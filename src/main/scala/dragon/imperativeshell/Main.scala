package dragon.imperativeshell

import dragon.imperativeshell.displayDragonFrame

import javax.swing.SwingUtilities

@main def main(): Unit =
  // Create the frame/panel on the event dispatching thread.
  SwingUtilities.invokeLater(
    new Runnable():
      def run(): Unit = displayDragonFrame()
  )