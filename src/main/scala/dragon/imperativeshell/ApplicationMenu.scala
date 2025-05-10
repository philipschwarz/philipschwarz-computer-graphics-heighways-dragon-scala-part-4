package dragon.imperativeshell

import dragon.functionalcore.action.ApplicationAction
import dragon.imperativeshell.ApplicationMenu.menuItemDetails

import java.awt.event.{ActionListener, KeyEvent}
import java.awt.{Menu, MenuItem, MenuShortcut}

class ApplicationMenu(actionListener: ActionListener) extends Menu("Application"):

  menuItemDetails.foreach { case action -> keyEventNumber =>
    val item = MenuItem(action.toString, MenuShortcut(keyEventNumber))
    add(item)
    item.addActionListener(actionListener)
  }

object ApplicationMenu:

  private val menuItemDetails = List(
    ApplicationAction.Instructions -> KeyEvent.VK_I,
    ApplicationAction.StartDemo -> KeyEvent.VK_D,
    ApplicationAction.PauseDemo -> KeyEvent.VK_P,
    ApplicationAction.ResumeDemo -> KeyEvent.VK_R,
    ApplicationAction.StartAgain -> KeyEvent.VK_S,
    ApplicationAction.Quit -> KeyEvent.VK_Q
  )

  val asText: String =
    menuItemDetails
      .map { case (action, keyCode) =>
        s"CMD + ${KeyEvent.getKeyText(keyCode)} = ${action.text}"
      }
      .mkString("\n")
