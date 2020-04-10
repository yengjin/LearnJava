package design_pattern.proxy.static_proxy;

/**
 * 真实实体类 - Actor演员
 */
public class Actor implements Person
{
    private String content;
    public Actor(String content) {
        this.content = content;
    }

    @Override
    public void speak() {
        System.out.println(this.content);
    }
}
