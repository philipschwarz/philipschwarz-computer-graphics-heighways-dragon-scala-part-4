package dragon.imperativeshell

import dragon.functionalcore.{Demo, DragonConfiguration}
import dragon.functionalcore.action.{ApplicationAction, DemoAction, DragonAction}

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{MenuBar, MenuItem}
import javax.swing.JFrame
import scala.util.Try

class DragonFrame extends JFrame with ActionListener:

  private val panel = DragonPanel()
  add(panel)
  setTitle(panel.config.asText)
  setupMenuBar(this)
  private val demoTimer = setupDemoTimer(this)

  override def actionPerformed(e: ActionEvent): Unit = e.getSource match
    case item: MenuItem => handleMenuAction(item)
    case timer: DemoTimer => handleDemoStep(timer)
    case _ =>

  private def setupMenuBar(actionListener: ActionListener): Unit =
    val menuBar = MenuBar()
    menuBar.add(ApplicationMenu(actionListener))
    menuBar.add(DragonConfigMenu(actionListener))
    setMenuBar(menuBar)

  private def setupDemoTimer(actionListener: ActionListener): DemoTimer =
    val timer = DemoTimer(
      initialDelayBetweenSteps = 10,
      listener = actionListener,
      numberOfSteps = Demo.steps.length)
    timer.start()
    timer

  private def handleMenuAction(menuItem: MenuItem): Unit =
    val command = menuItem.getActionCommand
    val maybeDragonAction = Try(DragonAction.valueOf(command)).toOption
    val maybeApplicationAction = Try(ApplicationAction.valueOf(command)).toOption
    maybeDragonAction.orElse(maybeApplicationAction) match
      case Some(dragonAction:DragonAction) =>
        panel.config = panel.config.updated(dragonAction)
        setTitle(panel.config.asText)
        repaint()
      case Some(ApplicationAction.Demo) =>
        panel.config = DragonConfiguration()
        demoTimer.reStart()
      case Some(ApplicationAction.Quit) =>
        System.exit(0)
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