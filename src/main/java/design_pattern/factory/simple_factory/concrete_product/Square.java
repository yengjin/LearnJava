package design_pattern.factory.simple_factory.concrete_product;

import design_pattern.factory.simple_factory.product.Shape;

public class Square implements Shape {

    public Square() {
        System.out.println("Create Square");
    }
    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}
