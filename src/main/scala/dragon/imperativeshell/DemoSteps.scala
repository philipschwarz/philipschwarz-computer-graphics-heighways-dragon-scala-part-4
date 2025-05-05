package imperativeshell

import dragon.functionalcore.{DemoTimingAction, Direction}
import dragon.functionalcore.DragonConfiguration.DragonConfigChange

type DemoStep = DragonConfigChange | DemoTimingAction

object Demo:

  val steps: List[DemoStep] =
    List(
      List.fill(10)(DragonConfigChange.GrowOlder),
      List(DemoTimingAction.GoSlower),
      List.fill(10)(DragonConfigChange.GrowOlder),
      List.fill(10)(DemoTimingAction.Sleep),
      List.fill(4)(DragonConfigChange.GrowYounger),
      List.fill(5)(DragonConfigChange.MoveLeft),
      List.fill(2)(DragonConfigChange.MoveUp),
      List.fill(3)(DragonConfigChange.GrowLarger),
      List.fill(4)(DragonConfigChange.GrowYounger),
      List.fill(5)(DragonConfigChange.MoveRight),
      List.fill(2)(DragonConfigChange.MoveDown),
      List.fill(12)(DragonConfigChange.GrowLarger),
      List.fill(11)(DragonConfigChange.GrowYounger),
      List.fill(20)(DragonConfigChange.GrowLarger),
      List(DemoTimingAction.GoFaster),
      List.fill(80)(DragonConfigChange.MoveLeft),
      List.fill(40)(DragonConfigChange.MoveUp),
      List(DemoTimingAction.GoSlower),
      List.fill(7)(DragonConfigChange.GrowOlder),
      List.fill(10)(DragonConfigChange.GrowSmaller),
      List.fill(4)(DragonConfigChange.MoveRight),
      List.fill(2)(DragonConfigChange.MoveDown),
      List.fill(Direction.values.length)(
        List(
          List(DragonConfigChange.ChangeOrientation),
          List.fill(10)(DemoTimingAction.Sleep)
        ).flatten
      ).flatten,
      List(DragonConfigChange.ChangeColour),
      List.fill(10)(DemoTimingAction.Sleep),
      List(DragonConfigChange.ChangeColour),
      List.fill(10)(DemoTimingAction.Sleep),
      List.fill(3)(DragonConfigChange.GrowYounger),
      List.fill(10)(DragonConfigChange.GrowSmaller),
      List(DemoTimingAction.GoFaster),
      List.fill(14)(DragonConfigChange.MoveRight),
      List.fill(6)(DragonConfigChange.MoveDown),
      List(DemoTimingAction.GoSlower),
      List.fill(10)(DemoTimingAction.Sleep),
      List.fill(17)(DragonConfigChange.GrowSmaller),
      List.fill(5)(DragonConfigChange.GrowYounger),
      List.fill(10)(DemoTimingAction.Sleep),
      List.fill(20)(DragonConfigChange.GrowOlder),
    ).flatten