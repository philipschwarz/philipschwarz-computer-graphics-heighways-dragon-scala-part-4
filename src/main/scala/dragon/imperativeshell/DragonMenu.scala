package dragon.imperativeshell

import dragon.functionalcore.action.DragonAction
import dragon.imperativeshell.DragonMenu.menuItemDetails

import java.awt.event.{ActionListener, KeyEvent}
import java.awt.{Menu, MenuItem, MenuShortcut}

class DragonMenu(actionListener: ActionListener) extends Menu("Dragon Parameters"):

  menuItemDetails.foreach { case dragonAction -> (keyCode, withShift) =>
    val item = MenuItem(dragonAction.toString, MenuShortcut(keyCode, withShift))
    add(item)
    item.addActionListener(actionListener)
  }

object DragonMenu:

  private val menuItemDetails: List[(DragonAction, (keyCode: Int, withShift: Boolean))] =
    List(
      DragonAction.ChangeColourScheme -> (KeyEvent.VK_C, false),
      DragonAction.ChangeOrientation -> (KeyEvent.VK_O, false),
      DragonAction.GrowOlder -> (KeyEvent.VK_RIGHT, false),
      DragonAction.GrowYounger -> (KeyEvent.VK_LEFT, false),
      DragonAction.GrowLarger -> (KeyEvent.VK_UP, false),
      DragonAction.GrowSmaller -> (KeyEvent.VK_DOWN, false),
      DragonAction.MoveRight -> (KeyEvent.VK_RIGHT, true),
      DragonAction.MoveLeft -> (KeyEvent.VK_LEFT, true),
      DragonAction.MoveUp -> (KeyEvent.VK_UP, true),
      DragonAction.MoveDown -> (KeyEvent.VK_DOWN, true)
    )

  val asText: String =
    menuItemDetails
      .map { case (change, (keyCode, withShift)) =>
        s"CMD ${if withShift then " + SHIFT" else ""} + ${KeyEvent.getKeyText(keyCode)} = ${change.text}"
      }
      .mkString("\n")
