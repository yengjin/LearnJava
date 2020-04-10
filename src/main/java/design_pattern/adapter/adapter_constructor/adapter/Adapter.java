package design_pattern.adapter.adapter_constructor.adapter;

import design_pattern.adapter.adapter_constructor.adaptee.Adaptee;
import design_pattern.adapter.adapter_constructor.target.Target;

/**
 * Adapter 适配器类
 * 构造器方式注入Adaptee到Adapter
 */
public class Adapter implements Target {

    private Adaptee adaptee;

    // 重点, 通过构造方式注入
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void execute() {
        System.out.println("正在适配中...");
        // 间接方式调用
        this.adaptee.myHandle();
    }
}
