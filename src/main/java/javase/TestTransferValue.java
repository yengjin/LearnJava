package javase;

public class TestTransferValue {

    public void changeValue1(Integer age) {
        age = 30;
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        Integer age = 20;
        test.changeValue1(age);
        System.out.println(age);
    }
}
