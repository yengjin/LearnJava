package thread;

/**
 * 指令重排序Demo
 */
public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true; // 重排序后, flag=true可能先行于a=1 (两者没有数据依赖)
    }

    public void method02() {
        if (flag) {
            a += 5;
            System.out.println(a);
        }
    }
}
