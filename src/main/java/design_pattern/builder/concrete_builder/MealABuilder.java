package design_pattern.builder.concrete_builder;

import design_pattern.builder.builder.MealBuilder;

/**
 * 套餐A
 * 具体的Builder
 */
public class MealABuilder extends MealBuilder {

    @Override
    public void buildFood() {
        super.getMeal().setFood("薯条");
    }

    @Override
    public void buildDrink() {
        super.getMeal().setDrink("可乐");
    }
}
