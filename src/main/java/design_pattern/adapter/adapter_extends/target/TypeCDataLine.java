package design_pattern.adapter.adapter_extends.target;

/**
 * TypeC数据线类, 实现Target接口
 */
public class TypeCDataLine implements Target {
    @Override
    public void connection() {
        System.out.println("使用Type-C数据线进行连接...");
    }
}
