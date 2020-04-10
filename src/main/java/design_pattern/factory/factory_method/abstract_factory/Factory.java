package design_pattern.factory.factory_method.abstract_factory;

import design_pattern.factory.factory_method.abstract_product.Shape;

/**
 * 工厂方法模式
 * 抽象工厂(Abstract Factory)角色：
 * 是工厂方法模式的核心，与应用程序无关。任何在模式中创建的对象的工厂类必须实现这个接口。
 */
public interface Factory {
    public Shape getShape();
}
