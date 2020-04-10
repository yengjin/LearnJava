package design_pattern.prototype.prototype;

/**
 * Prototype
 * 复制功能接口, 继承Cloneable(只是告诉程序可以调用clone方法, 本身没有定义任何方法)
 */
public interface Product extends Cloneable {
    // 使用, 具体交给子类实现
    void use(String s);

    // 用于复制实例的方法
    Product createClone();
}
