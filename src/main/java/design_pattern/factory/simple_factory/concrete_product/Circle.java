package design_pattern.factory.simple_factory.concrete_product;

import design_pattern.factory.simple_factory.product.Shape;

public class Circle implements Shape {

    public Circle() {
        System.out.println("Create Circle");
    }
    @Override
    public void draw() {
        System.out.println("Draw Circle");
    }
}
