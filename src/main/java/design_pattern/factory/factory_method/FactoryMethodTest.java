package design_pattern.factory.factory_method;

import design_pattern.factory.factory_method.abstract_factory.Factory;
import design_pattern.factory.factory_method.concrete_factory.CircleFactory;
import design_pattern.factory.factory_method.concrete_factory.RectangleFactory;
import design_pattern.factory.factory_method.concrete_factory.SquareFactory;
import design_pattern.factory.factory_method.abstract_product.Shape;

/**
 * 工厂方法模式 - 测试类
 * 工厂方法模式是在工厂模式家族中使用的最多模式，一般项目中存在最多的就是这个模式。
 *
 * 工厂方法模式是简单工厂的仅一步深化，不再提供一个统一的工厂类来创建所有的对象，
 * 而是**针对不同的对象提供不同的工厂**。也就是说 **每个对象都有一个与之对应的工厂**.
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        // 圆类
        Factory circleFactory = new CircleFactory();
        Shape circle = circleFactory.getShape();
        circle.draw();
        // 长方形类
        Factory rectangleFactory = new RectangleFactory();
        Shape rectangle = rectangleFactory.getShape();
        rectangle.draw();
        // 正方形类
        Factory squareFactory = new SquareFactory();
        Shape square = squareFactory.getShape();
        square.draw();

    }
}
