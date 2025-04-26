package dragon.imperativeshell

import java.awt.Color
import javax.swing.{JFrame, WindowConstants}

def displayDragonFrame(): Unit =
  val panel = DragonPanel(lineColour = Color.red, backgroundColour = Color.black)
  JFrame.setDefaultLookAndFeelDecorated(true)
  val frame = new JFrame("Heighway's Dragon")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.setSize(1800,1200)
  frame.add(panel)
  frame.setVisible(true)