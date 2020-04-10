package design_pattern.builder;

import design_pattern.builder.builder.MealBuilder;
import design_pattern.builder.concrete_builder.MealABuilder;
import design_pattern.builder.director.KFCWaiter;
import design_pattern.builder.product.Meal;

/**
 * 建造者模式测试类
 */
public class BuilderPatternTest {

    public static void main(String[] args) {
        // 先指定一个套餐(Builder, 负责具体组装方法的实现)
        MealBuilder builder = new MealABuilder();
        // Director组织者, 负责调用组装方法, 控制组装过程
        KFCWaiter waiter = new KFCWaiter(builder);
        // 用户只需要调用组织者的接口即可, 组装过程是透明的
        Meal meal = waiter.construct();

        System.out.println(meal.getFood());
        System.out.println(meal.getDrink());
    }
}
