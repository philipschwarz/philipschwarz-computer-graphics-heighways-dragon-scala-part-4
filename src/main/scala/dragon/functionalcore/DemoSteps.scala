package dragon.functionalcore

import dragon.functionalcore.Direction
import dragon.functionalcore.action.{DemoAction, DragonAction}

type DemoStep = DragonAction | DemoAction

object Demo:

  val steps: List[DemoStep] =
    List(
      List.fill(10)(DragonAction.GrowOlder),
      List(DemoAction.GoSlower),
      List.fill(10)(DragonAction.GrowOlder),
      List.fill(10)(DemoAction.Sleep),
      List.fill(4)(DragonAction.GrowYounger),
      List.fill(5)(DragonAction.MoveLeft),
      List.fill(2)(DragonAction.MoveUp),
      List.fill(3)(DragonAction.GrowLarger),
      List.fill(4)(DragonAction.GrowYounger),
      List.fill(5)(DragonAction.MoveRight),
      List.fill(2)(DragonAction.MoveDown),
      List.fill(12)(DragonAction.GrowLarger),
      List.fill(11)(DragonAction.GrowYounger),
      List.fill(20)(DragonAction.GrowLarger),
      List(DemoAction.GoFaster),
      List.fill(80)(DragonAction.MoveLeft),
      List.fill(40)(DragonAction.MoveUp),
      List(DemoAction.GoSlower),
      List.fill(7)(DragonAction.GrowOlder),
      List.fill(10)(DragonAction.GrowSmaller),
      List.fill(4)(DragonAction.MoveRight),
      List.fill(2)(DragonAction.MoveDown),
      List.fill(Direction.values.length)(
        List(
          List(DragonAction.ChangeOrientation),
          List.fill(10)(DemoAction.Sleep)
        ).flatten
      ).flatten,
      List(DragonAction.ChangeColour),
      List.fill(10)(DemoAction.Sleep),
      List(DragonAction.ChangeColour),
      List.fill(10)(DemoAction.Sleep),
      List.fill(3)(DragonAction.GrowYounger),
      List.fill(10)(DragonAction.GrowSmaller),
      List(DemoAction.GoFaster),
      List.fill(14)(DragonAction.MoveRight),
      List.fill(6)(DragonAction.MoveDown),
      List(DemoAction.GoSlower),
      List.fill(10)(DemoAction.Sleep),
      List.fill(17)(DragonAction.GrowSmaller),
      List.fill(5)(DragonAction.GrowYounger),
      List.fill(10)(DemoAction.Sleep),
      List.fill(20)(DragonAction.GrowOlder),
    ).flatten