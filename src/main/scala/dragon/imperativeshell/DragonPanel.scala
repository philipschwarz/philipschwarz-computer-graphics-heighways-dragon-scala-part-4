package dragon.imperativeshell

import dragon.*
import dragon.functionalcore.*
import dragon.functionalcore.Direction.{East, South}

import java.awt.Graphics
import javax.swing.*

class DragonPanel(var config: DragonConfiguration = DragonConfiguration()) extends JPanel:

  override def paintComponent(g: Graphics): Unit =

    val panelHeight = getSize().height - 1
    val panelWidth = getSize().width - 1

    def draw(line: Line): Unit =
      val (ax, ay) = line.start.deviceCoords(panelHeight)
      val (bx, by) = line.end.deviceCoords(panelHeight)
      g.drawLine(ax, ay, bx, by)

    def drawDragon(start: Point, age: Int, length: Int, direction: Direction): Unit =
      Dragon(start, age, length, direction).path.lines
        .foreach(draw)

    config match
      case DragonConfiguration(age, length, xPos, yPos, startDirection, colourCombination) =>
        super.paintComponent(g)
        setBackground(colourCombination.backgroundColour)
        g.setColor(colourCombination.lineColour)
        val startPoint = startingPoint(xPos, yPos, panelHeight, panelWidth)
        drawDragon(startPoint, age, length, startDirection)

  private def startingPoint(xPos: Int, yPos: Int, panelHeight: Int, panelWidth: Int): Point =
    val panelCentre = Point(panelWidth / 2 + xPos, panelHeight / 2 + yPos)
    panelCentre
      .translate(South, panelHeight / 7)
      .translate(East, panelWidth / 5)