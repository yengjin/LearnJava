package design_pattern.factory.simple_factory.factory;

import design_pattern.factory.simple_factory.concrete_product.Circle;
import design_pattern.factory.simple_factory.concrete_product.Rectangle;
import design_pattern.factory.simple_factory.concrete_product.Square;
import design_pattern.factory.simple_factory.product.Shape;

/**
 * 简单工厂模式
 * 工厂(Factory)角色, 简单工厂模式的核心, 负责实现创建所有实例的逻辑
 * 缺点: 新增产品类, 需要修改getShape()方法, 不符合开放封闭原则
 */
public class ShapeFactoryByString {

    public static Shape getShape(String shapeType) {
        if (shapeType == null) return null;

        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
