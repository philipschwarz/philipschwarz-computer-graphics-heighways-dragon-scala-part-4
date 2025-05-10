package dragon.imperativeshell

import dragon.functionalcore.action.{ApplicationAction, DemoAction, DragonAction}
import dragon.functionalcore.{Demo, DragonConfiguration}

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.image.BufferedImage
import java.awt.{Dialog, MenuBar, MenuItem}
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*
import scala.util.Try

class DragonFrame(width: Int, height: Int) extends JFrame with ActionListener:

  private val demoTimer = createDemoTimer(this)
  private val panel = DragonPanel()

  initialiseFrame()

  if userSaysTheyWantToSeeDemo() then
    panel.config = DragonConfiguration.forDemo(width, height)
    demoTimer.beginDemo()
  else showMenuActionsDialog()

  override def actionPerformed(e: ActionEvent): Unit = e.getSource match
    case item: MenuItem => handleMenuAction(item)
    case timer: DemoTimer => handleDemoStep(timer)
    case _ => ()

  private def initialiseFrame(): Unit =
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setSize(width, height)
    add(panel)
    setTitle(panel.config.asText)
    setupMenuBar(this)
    setVisible(true)

  private def setupMenuBar(actionListener: ActionListener): Unit =
    val menuBar = MenuBar()
    menuBar.add(ApplicationMenu(actionListener))
    menuBar.add(DragonConfigMenu(actionListener))
    setMenuBar(menuBar)

  private def createDemoTimer(actionListener: ActionListener): DemoTimer =
    val timer = DemoTimer(
      initialDelayBetweenSteps = 10,
      listener = actionListener,
      numberOfSteps = Demo.numberOfSteps)
    timer

  private def userSaysTheyWantToSeeDemo(): Boolean =
    JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Show Demo?", "", JOptionPane.YES_NO_OPTION)

  private def handleMenuAction(menuItem: MenuItem): Unit =
    menuItem.getShortcut
    val command = menuItem.getActionCommand
    val maybeDragonAction = Try(DragonAction.valueOf(command)).toOption
    val maybeApplicationAction = Try(ApplicationAction.valueOf(command)).toOption
    maybeDragonAction.orElse(maybeApplicationAction) match
      case Some(action:DragonAction) => handleDragonMenuAction(action)
      case Some(action:ApplicationAction) => handleApplicationMenuAction(action)
      case None => ()

  private def handleDemoStep(demoTimer: DemoTimer): Unit =
    Demo.stepByNumber.get(demoTimer.getAndIncrementStepNumber()) match
      case None => ()
      case Some(DemoAction.GoFaster) => demoTimer.msDelayBetweenSteps = 25
      case Some(DemoAction.GoSlower) => demoTimer.msDelayBetweenSteps = 100
      case Some(DemoAction.Sleep) => ()
      case Some(change: DragonAction) =>
        panel.config = panel.config.updated(change)
        setTitle(panel.config.asText)
        repaint()
      case Some(DemoAction.End)=>
        panel.config = DragonConfiguration.initial
        setTitle(panel.config.asText)
        repaint()
        showMenuActionsDialog()

  private def handleDragonMenuAction(action: DragonAction): Unit =
    if !demoTimer.isRunning then
      if demoTimer.isPaused then demoTimer.endDemo()
      panel.config = panel.config.updated(action)
      setTitle(panel.config.asText)
      repaint()

  private def handleApplicationMenuAction(action: ApplicationAction): Unit = action match
    case ApplicationAction.Instructions =>
      if demoTimer.isRunning then demoTimer.pauseDemo()
      showMenuActionsDialog()
    case ApplicationAction.StartDemo =>
      if demoTimer.isRunning then demoTimer.endDemo()
      panel.config = DragonConfiguration.forDemo(width, height)
      demoTimer.beginDemo()
    case ApplicationAction.PauseDemo => demoTimer.pauseDemo()
    case ApplicationAction.ResumeDemo => demoTimer.resumeDemo()
    case ApplicationAction.StartAgain =>
      if demoTimer.isRunning then demoTimer.endDemo()
      panel.config = DragonConfiguration.initial
      setTitle(panel.config.asText)
      repaint()
    case ApplicationAction.Quit => System.exit(0)

  private def showMenuActionsDialog(): Unit =
    val message = "- APPLICATION -\n\n" + ApplicationMenu.asText + "\n\n" + "- DRAGON -\n\n" + DragonConfigMenu.asText
    JOptionPane.showMessageDialog(this, message, "INSTRUCTIONS", JOptionPane.DEFAULT_OPTION, null)
