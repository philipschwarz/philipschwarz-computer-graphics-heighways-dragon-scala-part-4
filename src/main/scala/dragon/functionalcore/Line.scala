package dragon.functionalcore

type Line = (Point, Point)

extension (line: Line)
  def start: Point = line(0)
  def end: Point = line(1)