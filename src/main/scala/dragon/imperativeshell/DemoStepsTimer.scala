package dragon.imperativeshell

import java.awt.event.ActionListener
import javax.swing.Timer

type Milliseconds = Int

class DemoStepsTimer(
  initialDelayBetweenSteps: Milliseconds,
  listener: ActionListener,
  numberOfSteps: Int                 
 ) extends Timer(initialDelayBetweenSteps, listener):

  private var delay: Milliseconds = initialDelayBetweenSteps

  private var stepCount: Int = 0

  def msDelayBetweenSteps: Milliseconds = delay
  def msDelayBetweenSteps_=(ms: Milliseconds): Unit =
    delay = ms
    setDelay(ms)

  def nextStepNumber(): Int =
    val stepNumber = stepCount
    stepCount = stepCount + 1
    if stepCount == numberOfSteps then stop()
    stepNumber