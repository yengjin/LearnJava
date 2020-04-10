package design_pattern.proxy.static_proxy;

/**
 * Agent代理类, 负责代理Actor被代理类
 */
public class Agent implements Person {
    private Actor actor;    // 被代理类, 持有
    private String before;  // 需要在调用被代理类的方法前增强的代码
    private String after;   // 需要在调用被代理类的方法之后增强的代码

    public Agent(Actor actor, String before, String after) {
        this.actor = actor;
        this.before = before;
        this.after = after;
    }
    @Override
    public void speak() {
        // before speak, 代码增强
        System.out.println("Before actor speak, Agent say: " + before);
        actor.speak();
        // after speak, 代码增强
        System.out.println("Before actor speak, Agent say: " + after);
    }
}
