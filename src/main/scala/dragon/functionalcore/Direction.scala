package dragon.functionalcore

enum Direction:
  case North, East, South, West

  def rotated: Direction = this match
    case Direction.North => Direction.West
    case Direction.East  => Direction.North
    case Direction.South => Direction.East
    case Direction.West  => Direction.South
