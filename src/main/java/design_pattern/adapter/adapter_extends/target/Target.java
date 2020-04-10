package design_pattern.adapter.adapter_extends.target;

/**
 * Target 目标接口
 * 也就是具体业务最终要调的接口, 希望Adaptee被统一成Target接口
 */
public interface Target {
    void connection();
}
