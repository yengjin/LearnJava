package design_pattern.factory.factory_method.concrete_product;

import design_pattern.factory.factory_method.abstract_product.Shape;

/**
 * 具体产品(Concrete Product)角色 ：
 * 这个角色**实现了抽象产品角色所定义的接口**。
 * 某具体产品有专门的具体工厂创建，它们之间往往一一对应
 */
public class Square implements Shape {

    public Square() {
        System.out.println("Create Square");
    }
    @Override
    public void draw() {
        System.out.println("Draw Square");
    }
}
