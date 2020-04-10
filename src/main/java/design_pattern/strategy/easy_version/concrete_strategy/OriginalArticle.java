package design_pattern.strategy.easy_version.concrete_strategy;

import design_pattern.strategy.easy_version.strategy.IncreaseFansStrategy;

/**
 * 具体策略类ConcreteStrategy
 * 涨粉策略之一: 写原创文章
 */
public class OriginalArticle implements IncreaseFansStrategy {

    @Override
    public void action() {
        System.out.println("认真写原创, 最新文章: 策略模式, 就这?");
    }
}
