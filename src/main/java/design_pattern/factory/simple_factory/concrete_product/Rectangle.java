package design_pattern.factory.simple_factory.concrete_product;

import design_pattern.factory.simple_factory.product.Shape;

public class Rectangle implements Shape {

    public Rectangle() {
        System.out.println("Create Rectangle");
    }
    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
    }
}
