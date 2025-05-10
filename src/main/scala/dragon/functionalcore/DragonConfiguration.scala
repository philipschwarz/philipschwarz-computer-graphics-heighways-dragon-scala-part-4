package dragon.functionalcore

import dragon.functionalcore.action.DragonAction

import java.text.NumberFormat

case class DragonConfiguration(
    age: Int,
    length: Int,
    xPos: Int,
    yPos: Int,
    startDirection: Direction,
    colourCombination: ColourCombination
):

  private val numberFormatter: NumberFormat = NumberFormat.getNumberInstance

  def updated(change: DragonAction): DragonConfiguration = change match
    case DragonAction.GrowOlder if age < 20      => copy(age = age + 1)
    case DragonAction.GrowYounger if age > 0     => copy(age = age - 1)
    case DragonAction.GrowLarger if length < 500 => copy(length = length + Math.max(1, length / 10))
    case DragonAction.GrowSmaller if length > 1  => copy(length = length - Math.max(1, length / 10))
    case DragonAction.MoveRight if xPos < 1_000  => copy(xPos = xPos + 10 * age)
    case DragonAction.MoveLeft if xPos > -1_000  => copy(xPos = xPos - 10 * age)
    case DragonAction.MoveUp if yPos < 1_000     => copy(yPos = yPos + 10 * age)
    case DragonAction.MoveDown if yPos > -1_000  => copy(yPos = yPos - 10 * age)
    case DragonAction.ChangeColourScheme         => copy(colourCombination = colourCombination.next)
    case DragonAction.ChangeOrientation          => copy(startDirection = startDirection.next)
    case _                                       => this

  def asText: String =
    val separator = "    "
    s"Age: $age" + separator +
      s"Line length: ${numberFormatter.format(length)}" + separator +
      s"Number of lines: ${numberFormatter.format(Math.pow(2, age))}" + separator +
      s"Start position: x=$xPos y=$yPos" + separator +
      s"Start direction: $startDirection"

object DragonConfiguration:

  val initial: DragonConfiguration = DragonConfiguration(
    age = 0,
    length = 100,
    xPos = 0,
    yPos = 0,
    startDirection = Direction.East,
    colourCombination = ColourCombination.RedOnBlack
  )

  def forDemo(width: Int, height: Int): DragonConfiguration = DragonConfiguration(
    age = 0,
    length = 1,
    xPos = width / 5,
    yPos = -height / 12,
    startDirection = Direction.East,
    colourCombination = ColourCombination.RedOnBlack
  )
