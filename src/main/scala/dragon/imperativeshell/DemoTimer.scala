package dragon.imperativeshell

import java.awt.event.ActionListener
import javax.swing.Timer

type Milliseconds = Int

class DemoTimer(
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

  def stepNumber(): Int =
    val stepNumber = stepCount
    stepCount = stepCount + 1
    if stepCount == numberOfSteps then stop()
    stepNumber

  def pauseDemo(): Unit =
    if isRunning then stop()

  def resumeDemo(): Unit = {
    if isPaused then start()
  }

  def beginDemo(): Unit =
    if !isRunning then
      stepCount = 0
      msDelayBetweenSteps = initialDelayBetweenSteps
      start()

  def endDemo(): Unit =
    stepCount = numberOfSteps
    stop()
    
  def isPaused: Boolean =
    !isRunning && (1 until numberOfSteps).contains(stepCount) 
