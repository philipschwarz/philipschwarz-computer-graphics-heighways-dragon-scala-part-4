package dragon.imperativeshell

import java.awt.MenuBar
import java.awt.event.ActionListener
import javax.swing.{JFrame, WindowConstants}

class DragonFrame extends JFrame:
  val panel = DragonPanel(this)
  add(panel)
  setTitle(panel.dragonConfig.asText)
  setupMenuBar(panel)

  private def setupMenuBar(actionListener: ActionListener): Unit =
    val menuBar = MenuBar()
    menuBar.add(DragonConfigMenu(actionListener))
    setMenuBar(menuBar)