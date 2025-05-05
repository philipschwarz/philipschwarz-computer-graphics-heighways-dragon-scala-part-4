package dragon.imperativeshell

import dragon.functionalcore.{Demo, DragonConfiguration}
import dragon.functionalcore.action.{ApplicationAction, DemoAction, DragonAction}

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{MenuBar, MenuItem}
import javax.swing.{JFrame, JOptionPane, WindowConstants}
import scala.util.Try

class DragonFrame(width: Int, height: Int) extends JFrame with ActionListener:

  private val demoTimer = createDemoTimer(this)
  private val showingDemoAtStartupTime = askIfShowingDemo
  private val panel = DragonPanel()

  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  setSize(width, height)
  setVisible(true)

  add(panel)
  setTitle(panel.config.asText)
  setupMenuBar(this)

  if showingDemoAtStartupTime then
    panel.config = DragonConfiguration.forDemo(width, height)
    demoTimer.beginDemo()

  override def actionPerformed(e: ActionEvent): Unit = e.getSource match
    case item: MenuItem => handleMenuAction(item)
    case timer: DemoTimer => handleDemoStep(timer)
    case _ =>

  private def setupMenuBar(actionListener: ActionListener): Unit =
    val menuBar = MenuBar()
    menuBar.add(ApplicationMenu(actionListener))
    menuBar.add(DragonConfigMenu(actionListener))
    setMenuBar(menuBar)

  private def createDemoTimer(actionListener: ActionListener): DemoTimer =
    val timer = DemoTimer(
      initialDelayBetweenSteps = 10,
      listener = actionListener,
      numberOfSteps = Demo.steps.length)
    timer

  private def askIfShowingDemo: Boolean =
    JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Show Demo?", "", JOptionPane.YES_NO_OPTION)

  private def handleMenuAction(menuItem: MenuItem): Unit =
    val command = menuItem.getActionCommand
    val maybeDragonAction = Try(DragonAction.valueOf(command)).toOption
    val maybeApplicationAction = Try(ApplicationAction.valueOf(command)).toOption
    maybeDragonAction.orElse(maybeApplicationAction) match
      case Some(dragonAction:DragonAction) =>
        if !demoTimer.isRunning then
          if demoTimer.isPaused then demoTimer.endDemo()
          panel.config = panel.config.updated(dragonAction)
          setTitle(panel.config.asText)
          repaint()
      case Some(ApplicationAction.StartDemo) =>
        if demoTimer.isRunning then demoTimer.endDemo()
        panel.config = DragonConfiguration.forDemo(width, height)
        demoTimer.beginDemo()
      case Some(ApplicationAction.PauseDemo) => demoTimer.pauseDemo()
      case Some(ApplicationAction.ResumeDemo) => demoTimer.resumeDemo()
      case Some(ApplicationAction.StartAgain) =>
        if demoTimer.isRunning then demoTimer.endDemo()
        panel.config = DragonConfiguration.initial
        setTitle(panel.config.asText)
        repaint()
      case Some(ApplicationAction.Quit) => System.exit(0)
      case None => ()

  private def handleDemoStep(stepsTimer: DemoTimer): Unit =
    val stepNumber = stepsTimer.stepNumber()
    Demo.steps(stepNumber) match
      case DemoAction.GoFaster => stepsTimer.msDelayBetweenSteps = 25
      case DemoAction.GoSlower => stepsTimer.msDelayBetweenSteps = 100
      case DemoAction.Sleep => ()
      case change: DragonAction => panel.config = panel.config.updated(change)
        setTitle(panel.config.asText)
        repaint()