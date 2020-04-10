package design_pattern.factory.simple_factory.factory;

import design_pattern.factory.simple_factory.concrete_product.Circle;
import design_pattern.factory.simple_factory.product.Shape;

/**
 * 简单工厂模式
 * 工厂(Factory)角色, 简单工厂模式的核心, 负责实现创建所有实例的逻辑
 * 使用反射机制
 */
public class ShapeFactoryByReflect {

    public static Object getClass(Class<? extends Shape> clazz) {
        Object obj = null;

        try {
            obj = Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        Circle circle = (Circle) ShapeFactoryByReflect.getClass(Circle.class);
    }
}
