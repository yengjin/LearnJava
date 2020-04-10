package design_pattern.builder.concrete_builder;

import design_pattern.builder.builder.MealBuilder;

/**
 * 套餐B
 * 具体的Builder
 */
public class MealBBuilder extends MealBuilder {
    @Override
    public void buildFood() {
        super.getMeal().setFood("鸡翅");
    }

    @Override
    public void buildDrink() {
        super.getMeal().setFood("果茶");
    }
}
