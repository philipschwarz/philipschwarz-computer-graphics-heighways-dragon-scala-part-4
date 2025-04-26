package dragon.imperativeshell

import dragon.functionalcore.DragonConfiguration.Change as ConfigChange

import java.awt.event.{ActionListener, KeyEvent}
import java.awt.{Menu, MenuItem, MenuShortcut}

class DragonConfigMenu(actionListener: ActionListener) extends Menu("Configuration"):

  private val menuItemDetails = List(
    ConfigChange.GrowOlder -> KeyEvent.VK_CLOSE_BRACKET,
    ConfigChange.GrowYounger -> KeyEvent.VK_OPEN_BRACKET,
    ConfigChange.GrowLarger -> KeyEvent.VK_EQUALS,
    ConfigChange.GrowSmaller -> KeyEvent.VK_MINUS,
    ConfigChange.MoveRight -> KeyEvent.VK_RIGHT,
    ConfigChange.MoveLeft -> KeyEvent.VK_LEFT,
    ConfigChange.MoveUp -> KeyEvent.VK_UP,
    ConfigChange.MoveDown -> KeyEvent.VK_DOWN,
    ConfigChange.ChangeColour -> KeyEvent.VK_C,
    ConfigChange.ChangeOrientation -> KeyEvent.VK_O
  )

  menuItemDetails.foreach { case dragonChange -> keyEventNumber =>
    val item = MenuItem(dragonChange.toString, MenuShortcut(keyEventNumber))
    add(item)
    item.addActionListener(actionListener)
  }
