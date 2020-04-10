package design_pattern.builder.builder;

import design_pattern.builder.product.Meal;

/**
 * Builder 抽象建造者
 * 创建一个Product的各个部件指定的抽象接口
 */
public abstract class MealBuilder {
    private Meal meal = new Meal();     // 创建一个实例, 准备组装

    public abstract void buildFood();   // 每个子builder根据自己的情况, 重写

    public abstract void buildDrink();  // 每个子builder根据自己的情况, 重写

    // 返回meal实例
    public Meal getMeal() {
        return this.meal;
    }
}
