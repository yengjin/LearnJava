package design_pattern.proxy.dynamic_proxy;

import org.apache.log4j.EnhancedThrowableRenderer;

/**
 * JDK动态代理
 * 基于反射
 * 缺点: 被代理类一定是实现了某个接口的
 * 可以使用CGLIB
 */
public class ProxyByReflectTest {

    public static void main(String[] args) {
        // 注意一定要返回接口, 不能返回实现类
        Fruit fruit = (Fruit) DynamicAgent.agent(Fruit.class, new Apple());
        fruit.show();
    }
}
