package design_pattern.builder.director;

import design_pattern.builder.builder.MealBuilder;
import design_pattern.builder.product.Meal;

/**
 * Director 指挥者
 * 负责控制产品对象的生产过程
 * 隔离了客户与对象的生产过程
 */
public class KFCWaiter {
    private MealBuilder mealBuilder;

    public KFCWaiter(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public Meal construct() {
        // 准备食物
        mealBuilder.buildFood();

        // 准备饮料
        mealBuilder.buildDrink();

        // 准备完毕, 返回完整的套餐给用户
        return mealBuilder.getMeal();
    }
}
