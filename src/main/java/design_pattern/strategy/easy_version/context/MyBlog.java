package design_pattern.strategy.easy_version.context;

import design_pattern.strategy.easy_version.strategy.IncreaseFansStrategy;

/**
 * Context上下文
 */
public class MyBlog {

    // 涨粉策略
    private IncreaseFansStrategy strategy;

    public MyBlog(IncreaseFansStrategy strategy) {
        this.strategy = strategy;
    }

    // 调用策略
    public void exec() {
        // 上下文负责调用策略的具体算法
        strategy.action();
    }
}
