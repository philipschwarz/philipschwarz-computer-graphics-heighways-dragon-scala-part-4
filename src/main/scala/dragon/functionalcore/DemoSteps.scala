package dragon.functionalcore

import dragon.functionalcore.Direction
import dragon.functionalcore.action.{DemoAction, DragonAction}

object Demo:

  type Step = DragonAction | DemoAction

  def numberOfSteps = stepByNumber.size

  val stepByNumber: Map[Int, Step] =
    List(
      List.fill(10)(DragonAction.GrowOlder),
      List.fill(4)(DemoAction.GoSlower),
      List.fill(10)(DragonAction.GrowOlder),
      List.fill(10)(DemoAction.Sleep),
      List.fill(4)(DemoAction.GoSlower),
      List.fill(4)(DragonAction.GrowYounger),
      List.fill(5)(DragonAction.MoveLeft),
      List.fill(2)(DragonAction.MoveUp),
      List.fill(3)(DragonAction.GrowLarger),
      List.fill(4)(DragonAction.GrowYounger),
      List.fill(5)(DragonAction.MoveRight),
      List.fill(2)(DragonAction.MoveDown),
      List.fill(12)(DragonAction.GrowLarger),
      List.fill(11)(DragonAction.GrowYounger),
      List.fill(8)(DemoAction.GoFaster),
      List.fill(20)(DragonAction.GrowLarger),
      List.fill(80)(DragonAction.MoveLeft),
      List.fill(40)(DragonAction.MoveUp),
      List.fill(4)(DemoAction.GoSlower),
      List.fill(7)(DragonAction.GrowOlder),
      List.fill(11)(DragonAction.GrowSmaller),
      List.fill(4)(DragonAction.MoveRight),
      List.fill(2)(DragonAction.MoveDown),
      List
        .fill(Direction.values.length)(
          List(
            List(DragonAction.ChangeOrientation),
            List.fill(10)(DemoAction.Sleep)
          ).flatten
        )
        .flatten,
      List(DragonAction.ChangeColourScheme),
      List.fill(10)(DemoAction.Sleep),
      List(DragonAction.ChangeColourScheme),
      List.fill(10)(DemoAction.Sleep),
      List.fill(3)(DragonAction.GrowYounger),
      List.fill(9)(DragonAction.GrowSmaller),
      List.fill(12)(DemoAction.GoFaster),
      List.fill(14)(DragonAction.MoveRight),
      List.fill(6)(DragonAction.MoveDown),
      List.fill(10)(DemoAction.Sleep),
      List.fill(17)(DragonAction.GrowSmaller),
      List.fill(5)(DragonAction.GrowYounger),
      List.fill(10)(DemoAction.Sleep),
      List.fill(20)(DragonAction.GrowOlder),
      List.fill(10)(DemoAction.GoSlower),
      List.fill(10)(DemoAction.Sleep),
      List(DemoAction.End)
    ).flatten.zipWithIndex.map { case (v, k) => k -> v }.toMap
