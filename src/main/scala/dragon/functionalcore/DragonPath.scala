package dragon.functionalcore

type DragonPath = List[Point]

extension (path: DragonPath)
  def lines: List[Line] =
    if path.length < 2 then Nil
    else path.zip(path.tail)