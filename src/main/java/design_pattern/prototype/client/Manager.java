package design_pattern.prototype.client;

import design_pattern.prototype.prototype.Product;

import java.util.HashMap;

/**
 * Client
 * 原型注册中心
 * 使用Product接口来复制实例
 */
public class Manager {
    // 保存实例的名字和实例之间的关系
    private HashMap<String, Product> showcase = new HashMap<>();
    // register方法将接收到的一组 名字和Product接口, 注册到showcase中
    public void register(String name, Product product) {
        showcase.put(name, product);
    }
    public Product create(String productname) {
        Product p = showcase.get(productname);
        return p.createClone();         // 进行创建
    }
}

