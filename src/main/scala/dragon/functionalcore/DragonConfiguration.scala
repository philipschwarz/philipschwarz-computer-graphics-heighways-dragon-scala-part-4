package dragon.functionalcore

import dragon.functionalcore.DragonConfiguration.DragonConfigChange

import java.text.NumberFormat

case class DragonConfiguration(
  age: Int = 0,
  length: Int = 1,
  xPos: Int = 0,
  yPos: Int = 0,
  startDirection: Direction = Direction.East,
  colourCombination: ColourCombination = ColourCombination.RedOnBlack
):

  private val numberFormatter: NumberFormat = NumberFormat.getNumberInstance

  def updated(change: DragonConfigChange): DragonConfiguration = change match
    case DragonConfigChange.GrowOlder if age < 20      => copy(age = age + 1)
    case DragonConfigChange.GrowYounger if age > 0     => copy(age = age - 1)
    case DragonConfigChange.GrowLarger if length < 500 => copy(length = length + Math.max(1, length / 10))
    case DragonConfigChange.GrowSmaller if length > 1  => copy(length = length - Math.max(1, length / 10))
    case DragonConfigChange.MoveRight if xPos < 1_000  => copy(xPos = xPos + 10 * age)
    case DragonConfigChange.MoveLeft if xPos > -1_000  => copy(xPos = xPos - 10 * age)
    case DragonConfigChange.MoveUp if yPos < 1_000     => copy(yPos = yPos + 10 * age)
    case DragonConfigChange.MoveDown if yPos > -1_000  => copy(yPos = yPos - 10 * age)
    case DragonConfigChange.ChangeColour               => copy(colourCombination = colourCombination.next)
    case DragonConfigChange.ChangeOrientation          => copy(startDirection = startDirection.next)
    case _                                             => this

  def asText: String =
    val separator = "    "
    s"Age: $age" + separator +
      s"Line length: ${numberFormatter.format(length)}" + separator +
      s"Number of lines: ${numberFormatter.format(Math.pow(2, age))}" + separator +
      s"Start position: x=$xPos y=$yPos" + separator +
      s"Start direction: $startDirection"

object DragonConfiguration:

  enum DragonConfigChange:
    case
      GrowOlder,
      GrowYounger,
      GrowLarger,
      GrowSmaller,
      MoveRight,
      MoveLeft,
      MoveUp,
      MoveDown,
      ChangeColour,
      ChangeOrientation