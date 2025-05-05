package dragon.imperativeshell

import dragon.functionalcore.action.ApplicationAction

import java.awt.event.{ActionListener, KeyEvent}
import java.awt.{Menu, MenuItem, MenuShortcut}

class ApplicationMenu(actionListener: ActionListener) extends Menu("Application"):

  private val menuItemDetails = List(
    ApplicationAction.StartDemo -> KeyEvent.VK_D,
    ApplicationAction.PauseDemo -> KeyEvent.VK_P,
    ApplicationAction.ResumeDemo -> KeyEvent.VK_R,
    ApplicationAction.StartAgain -> KeyEvent.VK_S,
    ApplicationAction.Quit -> KeyEvent.VK_D,
  )

  menuItemDetails.foreach { case action -> keyEventNumber =>
    val item = MenuItem(action.toString, MenuShortcut(keyEventNumber))
    add(item)
    item.addActionListener(actionListener)
  }