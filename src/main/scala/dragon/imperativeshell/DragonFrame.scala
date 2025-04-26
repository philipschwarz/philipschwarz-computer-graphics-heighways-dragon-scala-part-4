package dragon.imperativeshell

import dragon.functionalcore.DragonConfiguration

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{MenuBar, MenuItem}
import javax.swing.JFrame

class DragonFrame extends JFrame with ActionListener :
  val panel = DragonPanel()
  add(panel)
  setTitle(panel.dragonConfig.asText)
  setupMenuBar(this)

  private def setupMenuBar(actionListener: ActionListener): Unit =
    val menuBar = MenuBar()
    menuBar.add(DragonConfigMenu(actionListener))
    setMenuBar(menuBar)

  override def actionPerformed(e: ActionEvent): Unit =
    if e.getSource.isInstanceOf[MenuItem] then
      val command = e.getSource.asInstanceOf[MenuItem].getActionCommand
      val configChange = DragonConfiguration.Change.valueOf(command)
      panel.dragonConfig = panel.dragonConfig.updated(configChange)
      setTitle(panel.dragonConfig.asText)
      repaint()