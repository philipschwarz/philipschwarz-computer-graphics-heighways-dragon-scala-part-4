package dragon.imperativeshell

import dragon.*
import dragon.functionalcore.*
import dragon.functionalcore.Direction.{East, South}

import java.awt.Graphics
import javax.swing.*

class DragonPanel(var dragonConfig: DragonConfiguration = DragonConfiguration()) extends JPanel :

  override def paintComponent(g: Graphics): Unit =

    val panelHeight = getSize().height - 1

    def startPoint(xPos: Int, yPos: Int): Point =
      val panelWidth = getSize().width - 1
      val panelCentre = Point(panelWidth / 2 + xPos, panelHeight / 2 + yPos)
      panelCentre
        .translate(South, panelHeight / 7)
        .translate(East, panelWidth / 5)

    def draw(line: Line): Unit =
      val (ax, ay) = line.start.deviceCoords(panelHeight)
      val (bx, by) = line.end.deviceCoords(panelHeight)
      g.drawLine(ax, ay, bx, by)

    def drawDragon(start: Point, age: Int, length: Int, direction: Direction): Unit =
      Dragon(start, age, length, direction).path.lines
        .foreach(draw)

    dragonConfig match
      case DragonConfiguration(age, length, xPos, yPos, startDirection, colourCombination) =>
        super.paintComponent(g)
        setBackground(colourCombination.backgroundColour)
        g.setColor(colourCombination.lineColour)
        drawDragon(startPoint(xPos, yPos), age, length, startDirection)