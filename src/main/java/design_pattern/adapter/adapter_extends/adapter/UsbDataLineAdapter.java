package design_pattern.adapter.adapter_extends.adapter;

import design_pattern.adapter.adapter_extends.adaptee.UsbDataLine;
import design_pattern.adapter.adapter_extends.target.Target;

/**
 * Adapter 适配者类
 * 继承UsbDataLine, 实现Target
 * 也就是实现Target接口, 然后在内部实际调的是UsbDataLine(父类)的业务逻辑
 */
public class UsbDataLineAdapter extends UsbDataLine implements Target {

    @Override
    public void connection() {
        // 实际上调被适配者(Adaptee)的业务方法
        System.out.println("正在进行USB到Type-C的转换...");
        super.usbConnect();
    }
}
