package design_pattern.adapter.adapter_extends.adaptee;

/**
 * Adaptee 被适配者
 * USB数据线, 需要被适配成TYPE-C接口
 */
public class UsbDataLine {

    public void usbConnect() {
        System.out.println("正在使用USB数据线连接...");
    }
}
