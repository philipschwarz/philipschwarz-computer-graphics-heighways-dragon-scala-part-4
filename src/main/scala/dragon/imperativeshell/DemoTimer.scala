package dragon.imperativeshell

import java.awt.event.ActionListener
import javax.swing.Timer

type Milliseconds = Int

class DemoTimer(
    initialDelayBetweenSteps: Milliseconds,
    listener: ActionListener,
    numberOfSteps: Int
) extends Timer(initialDelayBetweenSteps, listener):

  private var currentStepNumber: Int = 0

  def msDelayBetweenSteps: Milliseconds = getDelay
  def msDelayBetweenSteps_=(ms: Milliseconds): Unit = setDelay(ms)

  def getAndIncrementStepNumber(): Int =
    val stepNumber = currentStepNumber
    currentStepNumber = currentStepNumber + 1
    if currentStepNumber == numberOfSteps then stop()
    stepNumber

  def pauseDemo(): Unit = if isRunning then stop()

  def resumeDemo(): Unit = if isPaused then start()

  def beginDemo(): Unit =
    if !isRunning then
      currentStepNumber = 0
      msDelayBetweenSteps = initialDelayBetweenSteps
      start()

  def endDemo(): Unit =
    currentStepNumber = numberOfSteps
    stop()

  def isPaused: Boolean =
    !isRunning && currentStepNumber > 0 && currentStepNumber < numberOfSteps
