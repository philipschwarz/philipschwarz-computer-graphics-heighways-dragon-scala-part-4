package dragon.imperativeshell

import javax.swing.{JFrame, SwingUtilities, WindowConstants}

@main def main(): Unit =
  // Create the frame/panel on the event dispatching thread.
  SwingUtilities.invokeLater(
    new Runnable():
      def run(): Unit = displayDragonFrame()
  )

def displayDragonFrame(): Unit =
  JFrame.setDefaultLookAndFeelDecorated(true)
  val frame = new DragonFrame()
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.setSize(2000, 2000)
  frame.setVisible(true)  