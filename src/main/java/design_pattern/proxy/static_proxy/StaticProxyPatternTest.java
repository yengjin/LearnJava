package design_pattern.proxy.static_proxy;

/**
 * 代理设计模式
 * 有点像适配器模式
 * 角色:
 * 1. 代理类
 * 2. 被代理类
 * 3. 被代理类实现的接口
 *
 * 过程: 代理类也实现和被代理类一样的接口,
 * 之后在调用被代理类方法前后可以自行添加代码, 用于代码增强
 */
public class StaticProxyPatternTest {
    public static void main(String[] args) {
        Actor actor = new Actor("I am a famous actor!");
        Agent agent = new Agent(actor, "Hello I am agent.", "That's all!");
        agent.speak();      // 调用了代理类的speak, 代理类会调被代理类的speak.
    }
}
