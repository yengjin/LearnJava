package design_pattern.prototype.concrete_prototype;


import design_pattern.prototype.prototype.Product;

/**
 * ConcreteProduct, 具体产品类, 需要实现原型方法(创建)
 */
public class MessageBox implements Product {

    private char decochar;

    public MessageBox(char decochar) {
        this.decochar = decochar;
    }


    @Override
    public void use(String s) {
        int length=s.getBytes().length;
        for (int i = 0; i < length+4; i++) {
            System.out.print(decochar);
        }
        System.out.println("");
        System.out.println(decochar+" "+s+" "+decochar);
        for (int i = 0; i < length+4; i++) {
            System.out.print(decochar);
        }
        System.out.println("");
    }

    // 该方法用于复制自己
    @Override
    public Product createClone() {
        Product p = null;
        try {
            // 调用Object.clone(), 返回一个复制对象
            p = (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
