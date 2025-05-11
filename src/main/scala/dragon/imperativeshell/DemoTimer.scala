package dragon.imperativeshell

import java.awt.event.ActionListener
import javax.swing.Timer

type Milliseconds = Int

class DemoTimer(
    initialMsDelayBetweenSteps: Milliseconds,
    listener: ActionListener,
    numberOfSteps: Int
) extends Timer(initialMsDelayBetweenSteps, listener):

  private var nextStepNumber: Int = 0

  def msDelayBetweenSteps: Milliseconds = getDelay
  def msDelayBetweenSteps_=(ms: Milliseconds): Unit = setDelay(ms)

  def getAndIncrementStepNumber(): Int =
    val stepNumber = nextStepNumber
    nextStepNumber = nextStepNumber + 1
    if nextStepNumber == numberOfSteps then stop()
    stepNumber

  def beginDemo(): Unit =
    if !isRunning then
      nextStepNumber = 0
      msDelayBetweenSteps = initialMsDelayBetweenSteps
      start()

  def pauseDemo(): Unit = if isRunning then stop()

  def resumeDemo(): Unit = if isPaused then start()

  def endDemo(): Unit =
    nextStepNumber = numberOfSteps
    stop()

  def isPaused: Boolean =
    !isRunning && nextStepNumber > 0 && nextStepNumber < numberOfSteps
