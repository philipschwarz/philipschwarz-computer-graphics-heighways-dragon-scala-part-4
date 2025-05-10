package dragon.imperativeshell

import dragon.functionalcore.action.DragonAction
import dragon.imperativeshell.DragonConfigMenu.menuItemDetails

import java.awt.event.{ActionListener, KeyEvent}
import java.awt.{Menu, MenuItem, MenuShortcut}

class DragonConfigMenu(actionListener: ActionListener) extends Menu("Dragon Configuration"):

  menuItemDetails.foreach { case dragonChange -> (keyEventNumber, withShift) =>
    val item = MenuItem(dragonChange.toString, MenuShortcut(keyEventNumber, withShift))
    add(item)
    item.addActionListener(actionListener)
  }

object DragonConfigMenu:

  private val menuItemDetails: List[(DragonAction, (Int, Boolean))] = List(
    DragonAction.ChangeColourScheme -> (KeyEvent.VK_C,false),
    DragonAction.ChangeOrientation -> (KeyEvent.VK_O,false),
    DragonAction.GrowOlder -> (KeyEvent.VK_RIGHT,false),
    DragonAction.GrowYounger -> (KeyEvent.VK_LEFT,false),
    DragonAction.GrowLarger -> (KeyEvent.VK_UP,false),
    DragonAction.GrowSmaller -> (KeyEvent.VK_DOWN,false),
    DragonAction.MoveRight -> (KeyEvent.VK_RIGHT,true),
    DragonAction.MoveLeft -> (KeyEvent.VK_LEFT,true),
    DragonAction.MoveUp -> (KeyEvent.VK_UP,true),
    DragonAction.MoveDown -> (KeyEvent.VK_DOWN,true),
  )

  val asText: String =
    menuItemDetails.map { case (change, (keyCode, withShift)) =>
      s"CMD ${if withShift then " + SHIFT" else ""} + ${KeyEvent.getKeyText(keyCode)} = ${change.text}"
    }.mkString("\n")