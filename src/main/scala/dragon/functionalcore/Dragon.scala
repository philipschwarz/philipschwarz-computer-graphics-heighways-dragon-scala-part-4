package dragon.functionalcore

case class Dragon(startPoint: Point, age: Int, length: Int, direction: Direction):

  val path: DragonPath =
    DragonShape
      .initial(direction)
      .grow(age)
      .path(startPoint, length)
