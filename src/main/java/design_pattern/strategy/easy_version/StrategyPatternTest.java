package design_pattern.strategy.easy_version;

import design_pattern.strategy.easy_version.concrete_strategy.OriginalArticle;
import design_pattern.strategy.easy_version.concrete_strategy.WaterArmy;
import design_pattern.strategy.easy_version.context.MyBlog;

/**
 * 策略模式测试类
 * 优点: 算法自由切换, 改策略很方便. 增加策略只需要加一个策略类.
 * 缺点: 策略类的数量增多, 上层模块必须知道有哪些策略, 才能决定用哪种策略(策略类需要对外暴露)
 */
public class StrategyPatternTest {

    public static void main(String[] args) {
        MyBlog blog = new MyBlog(new WaterArmy());
        blog.exec();

        MyBlog blog1 = new MyBlog(new OriginalArticle());
        blog1.exec();

    }
}
