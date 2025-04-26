package dragon.functionalcore

import dragon.functionalcore.DragonConfiguration.Change

import java.text.NumberFormat

case class DragonConfiguration(
  age: Int = 0,
  length: Int = 50,
  xPos: Int = 0,
  yPos: Int = 0,
  startDirection: Direction = Direction.East,
  colourCombination: ColourCombination = ColourCombination.BlackOnWhite
):

  private val numberFormatter: NumberFormat = NumberFormat.getNumberInstance

  def updated(change: Change): DragonConfiguration = change match
    case Change.GrowOlder if age < 20      => copy(age = age + 1)
    case Change.GrowYounger if age > 0     => copy(age = age - 1)
    case Change.GrowLarger if length < 500 => copy(length = length + Math.max(1, length / 3))
    case Change.GrowSmaller if length > 1  => copy(length = length - Math.max(1, length / 3))
    case Change.MoveRight if xPos < 1_000  => copy(xPos = xPos + 10 * age)
    case Change.MoveLeft if xPos > -1_000  => copy(xPos = xPos - 10 * age)
    case Change.MoveUp if yPos < 1_000     => copy(yPos = yPos + 10 * age)
    case Change.MoveDown if yPos > -1_000  => copy(yPos = yPos - 10 * age)
    case Change.ChangeColour               => copy(colourCombination = colourCombination.next)
    case Change.ChangeOrientation          => copy(startDirection = startDirection.next)
    case _                                 => this

  def asText: String =
    val separator = "    "
    s"Age: $age" + separator +
      s"Line length: ${numberFormatter.format(length)}" + separator +
      s"Number of lines: ${numberFormatter.format(Math.pow(2, age))}" + separator +
      s"Start position: x=$xPos y=$yPos" + separator +
      s"Start direction: $startDirection"

object DragonConfiguration:

  enum Change:
    case GrowOlder,
      GrowYounger,
      GrowLarger,
      GrowSmaller,
      MoveRight,
      MoveLeft,
      MoveUp,
      MoveDown,
      ChangeColour,
      ChangeOrientation
