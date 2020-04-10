package design_pattern.adapter.adapter_constructor;

import design_pattern.adapter.adapter_constructor.adaptee.Adaptee;
import design_pattern.adapter.adapter_constructor.adapter.Adapter;
import design_pattern.adapter.adapter_constructor.target.Target;

/**
 * 适配器模式 测试类
 * 基于构造器注入的方式进行适配
 */
public class AdapterPatternTest {

    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.execute();
    }
}
