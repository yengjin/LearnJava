package design_pattern.factory.factory_method.concrete_factory;

import design_pattern.factory.factory_method.abstract_product.Shape;
import design_pattern.factory.factory_method.abstract_factory.Factory;
import design_pattern.factory.factory_method.concrete_product.Square;

/**
 * 具体工厂(Concrete Factory)角色:
 * 实现抽象工厂接口的具体工厂类，包含与应用程序密切相关的逻辑，
 * 并且受到应用程序调用以创建某一种产品对象。
 */
public class SquareFactory implements Factory {
    @Override
    public Shape getShape() {
        return new Square();
    }
}
