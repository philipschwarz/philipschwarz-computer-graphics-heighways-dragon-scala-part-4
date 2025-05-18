package dragon.imperativeshell

import dragon.functionalcore.action.{ApplicationAction, DemoAction, DragonAction}
import dragon.functionalcore.{Demo, DragonParameters}

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{MenuBar, MenuItem}
import javax.swing.*
import scala.util.Try

class DragonFrame(width: Int, height: Int) extends JFrame with ActionListener:

  private val demoTimer = createDemoTimer()
  private val panel = DragonPanel()

  initialiseFrame()

  if askUserIfTheyWantToSeeDemo() then
    panel.dragonParameters = DragonParameters.forDemo(width, height)
    demoTimer.beginDemo()
  else showInstructionsDialog()

  override def actionPerformed(e: ActionEvent): Unit = e.getSource match
    case item: MenuItem   => handleMenuAction(item)
    case _: DemoTimer     => handleDemoStep()
    case _                => ()

  private def handleMenuAction(menuItem: MenuItem): Unit =
    menuItem.getShortcut
    val command = menuItem.getActionCommand
    val maybeDragonAction = Try(DragonAction.valueOf(command)).toOption
    val maybeApplicationAction = Try(ApplicationAction.valueOf(command)).toOption
    maybeDragonAction.orElse(maybeApplicationAction) match
      case Some(action: DragonAction)      => performDragonAction(action)
      case Some(action: ApplicationAction) => performApplicationAction(action)
      case None                            => ()

  private def performDragonAction(action: DragonAction): Unit =
    if !demoTimer.isRunning then
      if demoTimer.isPaused then demoTimer.endDemo()
      panel.dragonParameters = panel.dragonParameters.updated(action)
      setTitle(panel.dragonParameters.asText)
      repaint()

  private def performApplicationAction(action: ApplicationAction): Unit = action match
    case ApplicationAction.Instructions =>
      if demoTimer.isRunning then demoTimer.pauseDemo()
      showInstructionsDialog()
    case ApplicationAction.StartDemo =>
      if demoTimer.isRunning then demoTimer.endDemo()
      panel.dragonParameters = DragonParameters.forDemo(width, height)
      demoTimer.beginDemo()
    case ApplicationAction.PauseDemo => demoTimer.pauseDemo()
    case ApplicationAction.ResumeDemo => demoTimer.resumeDemo()
    case ApplicationAction.StartAgain =>
      if demoTimer.isRunning then demoTimer.endDemo()
      panel.dragonParameters = DragonParameters.initial
      setTitle(panel.dragonParameters.asText)
      repaint()
    case ApplicationAction.Quit => System.exit(0)

  private def handleDemoStep(): Unit =
    Demo.stepByNumber.get(demoTimer.getAndIncrementStepNumber()) match
      case None => ()
      case Some(DemoAction.GoFaster) =>
        demoTimer.msDelayBetweenSteps = Math.max(demoTimer.msDelayBetweenSteps - 25, 25)
      case Some(DemoAction.GoSlower) => demoTimer.msDelayBetweenSteps += 25
      case Some(DemoAction.Sleep) => ()
      case Some(DemoAction.End) =>
        panel.dragonParameters = DragonParameters.initial
        setTitle(panel.dragonParameters.asText)
        repaint()
        showInstructionsDialog()
      case Some(dragonAction: DragonAction) =>
        panel.dragonParameters = panel.dragonParameters.updated(dragonAction)
        setTitle(panel.dragonParameters.asText)
        repaint()

  private def createDemoTimer(): DemoTimer =
    DemoTimer(
      initialMsDelayBetweenSteps = 25,
      listener = this,
      numberOfSteps = Demo.numberOfSteps
    )

  private def initialiseFrame(): Unit =
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setSize(width, height)
    add(panel)
    setTitle(panel.dragonParameters.asText)
    setupMenuBar()
    setVisible(true)

  private def setupMenuBar(): Unit =
    val menuBar = MenuBar()
    menuBar.add(ApplicationMenu(actionListener = this))
    menuBar.add(DragonMenu(actionListener = this))
    setMenuBar(menuBar)

  private def askUserIfTheyWantToSeeDemo(): Boolean =
    val icon  = new ImageIcon(getClass().getResource("/dragon.png"),"dragon")
    JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
      this,
      "Show Demo?",
      "Show Demo",
      JOptionPane.YES_NO_OPTION,
      JOptionPane.INFORMATION_MESSAGE,
      icon
    )

  private def showInstructionsDialog(): Unit =
    val message =
      "- APPLICATION -\n\n" + ApplicationMenu.asText + "\n\n" + "- DRAGON -\n\n" + DragonMenu.asText
    JOptionPane.showMessageDialog(this, message, "INSTRUCTIONS", JOptionPane.DEFAULT_OPTION, null)
