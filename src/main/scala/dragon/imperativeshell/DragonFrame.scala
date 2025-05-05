package dragon.imperativeshell

import dragon.functionalcore.DemoTimingAction
import dragon.functionalcore.DragonConfiguration.DragonConfigChange
import imperativeshell.Demo

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{MenuBar, MenuItem}
import javax.swing.JFrame

class DragonFrame extends JFrame with ActionListener:

  private val panel = DragonPanel()
  add(panel)
  setTitle(panel.config.asText)
  setupMenuBar(this)
  setupDemoStepTimer(this)

  override def actionPerformed(e: ActionEvent): Unit = e.getSource match
    case item: MenuItem => handleMenuAction(item)
    case timer: DemoStepsTimer => handleDemoStep(timer)
    case _ =>

  private def setupMenuBar(actionListener: ActionListener): Unit =
    val menuBar = MenuBar()
    menuBar.add(DragonConfigMenu(actionListener))
    setMenuBar(menuBar)

  private def setupDemoStepTimer(actionListener: ActionListener): Unit =
    val timer = DemoStepsTimer(
      initialDelayBetweenSteps = 10,
      listener = actionListener,
      numberOfSteps = Demo.steps.length)
    timer.start()

  private def handleMenuAction(menuItem: MenuItem): Unit =
    val command = menuItem.getActionCommand
    val configChange = DragonConfigChange.valueOf(command)
    panel.config = panel.config.updated(configChange)
    setTitle(panel.config.asText)
    repaint()

  private def handleDemoStep(stepsTimer: DemoStepsTimer): Unit =
    val stepNumber = stepsTimer.nextStepNumber()
    Demo.steps(stepNumber) match
      case DemoTimingAction.GoFaster => stepsTimer.msDelayBetweenSteps = 25
      case DemoTimingAction.GoSlower => stepsTimer.msDelayBetweenSteps = 100
      case DemoTimingAction.Sleep => ()
      case change: DragonConfigChange => panel.config = panel.config.updated(change)
        setTitle(panel.config.asText)
        repaint()