package dragon.imperativeshell

import dragon.functionalcore.action.ApplicationAction

import java.awt.event.{ActionListener, KeyEvent}
import java.awt.{Menu, MenuItem, MenuShortcut}

class ApplicationMenu(actionListener: ActionListener) extends Menu("Application"):

  private val menuItemDetails = List(
    ApplicationAction.Demo -> KeyEvent.VK_D,
    ApplicationAction.Quit -> KeyEvent.VK_D,
  )
  
  menuItemDetails.foreach { case action -> keyEventNumber =>
    val item = MenuItem(action.toString, MenuShortcut(keyEventNumber))
    add(item)
    item.addActionListener(actionListener)
  }  