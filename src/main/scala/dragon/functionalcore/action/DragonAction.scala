package dragon.functionalcore.action

enum DragonAction(val text: String):
  case ChangeColourScheme extends DragonAction("change colour scheme")
  case ChangeOrientation extends DragonAction("change orientation")
  case GrowOlder extends DragonAction("grow older")
  case GrowYounger extends DragonAction("grow younger")
  case GrowLarger extends DragonAction("grow larger")
  case GrowSmaller extends DragonAction("grow smaller")
  case MoveRight extends DragonAction("move right")
  case MoveLeft extends DragonAction("move left")
  case MoveUp extends DragonAction("move up")
  case MoveDown extends DragonAction("move down")
