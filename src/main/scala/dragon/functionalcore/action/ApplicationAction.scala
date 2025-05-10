package dragon.functionalcore.action

enum ApplicationAction(val text: String):
  case Instructions extends ApplicationAction("show instructions")
  case StartDemo extends ApplicationAction("start demo")
  case PauseDemo extends ApplicationAction("pause demo")
  case ResumeDemo extends ApplicationAction("resume demo")
  case StartAgain extends ApplicationAction("start again")
  case Quit extends ApplicationAction("quit")
