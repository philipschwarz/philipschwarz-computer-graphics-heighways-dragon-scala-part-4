package dragon.functionalcore

import scala.annotation.tailrec

type DragonShape = List[Direction]

object DragonShape:

  def initial(startDirection: Direction): DragonShape =
    List(startDirection)

extension (shape: DragonShape)

  @tailrec
  def grow(age: Int): DragonShape =
    if age == 0 then shape
    else (shape ++ shape.rotated).grow(age - 1)

  private def rotated: DragonShape =
    shape.reverse.map(_.rotated)

  def path(startPoint: Point, length: Int): DragonPath =
    shape.foldLeft(List(startPoint)): (path, direction) =>
      path.head.translate(direction, length) :: path
