package dragon.functionalcore

import java.awt.Color

enum ColourCombination(val lineColour: Color, val backgroundColour: Color):

  case BlackOnWhite extends ColourCombination(Color.black, Color.white)
  case GoldOnGreen extends ColourCombination(Color(255, 215, 0), Color(0, 128, 0))
  case WhiteOnCornFlowerBlue extends ColourCombination(Color.white, Color(100, 149, 237))
  case RedOnBlack extends ColourCombination(Color.red, Color.black)

  def next: ColourCombination =
    ColourCombination.fromOrdinal((ordinal + 1) % ColourCombination.values.length)
