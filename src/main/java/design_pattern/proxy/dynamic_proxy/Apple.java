package design_pattern.proxy.dynamic_proxy;

/**
 * 苹果类, 被代理类
 * 实现水果接口
 */
public class Apple implements Fruit {
    @Override
    public void show() {
        System.out.println("<<<<show method is invoked");
    }
}
