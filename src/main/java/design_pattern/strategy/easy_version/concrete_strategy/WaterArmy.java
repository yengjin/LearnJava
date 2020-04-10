package design_pattern.strategy.easy_version.concrete_strategy;

import design_pattern.strategy.easy_version.strategy.IncreaseFansStrategy;

/**
 * 具体策略类ConcreteStrategy
 * 涨粉策略之一: 请水军
 */
public class WaterArmy implements IncreaseFansStrategy {

    @Override
    public void action() {
        System.out.println("水军来也, 点赞转发加鸡腿!");
    }
}
