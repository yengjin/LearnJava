package design_pattern.adapter.adapter_extends;

import design_pattern.adapter.adapter_extends.adaptee.UsbDataLine;
import design_pattern.adapter.adapter_extends.adapter.UsbDataLineAdapter;
import design_pattern.adapter.adapter_extends.target.Target;
import design_pattern.adapter.adapter_extends.target.TypeCDataLine;

/**
 * 适配器模式 测试类
 * 基于继承的方式进行适配
 */
public class AdapterPatternTest {

    public static void main(String[] args) {
        // Target就是我们希望看到的接口
        Target typeCDataLine = new TypeCDataLine(); // 正常情况
        Target usbDataLineAdapter = new UsbDataLineAdapter();
        usbDataLineAdapter.connection();    // 实际内部走的是super.usbConnect();


        typeCDataLine.connection();
    }
}
