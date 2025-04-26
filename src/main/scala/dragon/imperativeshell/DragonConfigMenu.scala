package dragon.imperativeshell

import dragon.functionalcore.DragonConfiguration.Change as ConfigChange

import java.awt.event.{ActionListener, KeyEvent}
import java.awt.{Menu, MenuItem, MenuShortcut}

class DragonConfigMenu(actionListener: ActionListener) extends Menu("Configuration"):

  private val menuItemDetails = List(
    ConfigChange.GrowOlder -> KeyEvent.VK_RIGHT,
    ConfigChange.GrowYounger -> KeyEvent.VK_LEFT,
    ConfigChange.GrowLarger -> KeyEvent.VK_UP,
    ConfigChange.GrowSmaller -> KeyEvent.VK_DOWN,
    ConfigChange.MoveRight -> KeyEvent.VK_CLOSE_BRACKET,
    ConfigChange.MoveLeft -> KeyEvent.VK_OPEN_BRACKET,
    ConfigChange.MoveUp -> KeyEvent.VK_EQUALS,
    ConfigChange.MoveDown -> KeyEvent.VK_MINUS,
    ConfigChange.ChangeColour -> KeyEvent.VK_C,
    ConfigChange.ChangeOrientation -> KeyEvent.VK_O
  )

  menuItemDetails.foreach { case dragonChange -> keyEventNumber =>
    val item = MenuItem(dragonChange.toString, MenuShortcut(keyEventNumber))
    add(item)
    item.addActionListener(actionListener)
  }
