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
  new DragonFrame(width = 2000, height = 2000)