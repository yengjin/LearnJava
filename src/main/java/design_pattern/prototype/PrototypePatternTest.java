package design_pattern.prototype;

import design_pattern.prototype.client.Manager;
import design_pattern.prototype.concrete_prototype.MessageBox;
import design_pattern.prototype.concrete_prototype.UnderlinePen;
import design_pattern.prototype.prototype.Product;

/**
 * 角色:
 * 原型类Prototype: 所有具体类要实现的接口, 继承了Cloneable(为了支持复制), 定义了use, create
 * 客户端类/使用者Client: 负责进行**Product的注册, 及根据名字调用创建方法**, 方便利用名称去随时复制
 * 具体原型类ConcretePrototype: 实现原型类接口, 自己实现create方法(怎么复制)
 */
public class PrototypePatternTest {

    public static void main(String[] args) {
        // 原型注册中心
        Manager manager = new Manager();

        UnderlinePen underlinePen = new UnderlinePen('~');
        MessageBox mBox = new MessageBox('*');
        MessageBox sBox = new MessageBox('/');

        // 注册三种原型, HashMap
        manager.register("Strong message", underlinePen);
        manager.register("Waring Box", mBox);
        manager.register("Slash Box", sBox);

        // 通过客户端(注册中心)调用
        Product p1 = manager.create("Strong message");
        p1.use("hello world");
        Product p2 = manager.create("Waring Box");
        p2.use("hello world");
        Product p3 = manager.create("Slash Box");
        p3.use("hello world");
    }
}
