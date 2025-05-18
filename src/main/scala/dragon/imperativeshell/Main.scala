package dragon.imperativeshell

import javax.swing.{JFrame, SwingUtilities}

@main def main(): Unit =
  // Create the frame/panel on the event dispatching thread.
  SwingUtilities.invokeLater(
    new Runnable():
      def run(): Unit = displayDragonFrame()
  )

private def displayDragonFrame(): Unit =
  JFrame.setDefaultLookAndFeelDecorated(true)
  new DragonFrame(width = 2000, height = 2000)
