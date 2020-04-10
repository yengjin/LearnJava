package design_pattern.proxy.dynamic_proxy.CGLIB;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理
 */
public class CglibProxyTest {
    public static void main(String[] args) throws Exception {
        CglibTestSon cglibTestSon = new CglibTestSon();
        Enhancer enhancer = new Enhancer();
        Callback s = new MyInvoker(cglibTestSon);
        enhancer.setSuperclass(CglibTestSon.class);
        Callback[] callbacks = new Callback[]{ s };
        enhancer.setCallbacks(callbacks);
        CglibTestSon cglibTestSon2 = (CglibTestSon) enhancer.create();
        cglibTestSon2.gotoHome();
        cglibTestSon2.gotoSchool();

        cglibTestSon2.oneday();
        cglibTestSon2.onedayFinal();
    }
}

class MyInvoker implements MethodInterceptor {
    private CglibTestSon s;
    public MyInvoker(CglibTestSon s) {
        this.s = s;
    }
    private void aopMethod() {
        System.out.println("I am AOP Method!");
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        aopMethod();    // aop增强
        Object a = method.invoke(s, objects);   // 原来的函数
        return a;
    }
}
/**
 * 被代理的类, 不需要顶层接口
 */
class CglibTestSon {
    public CglibTestSon() {

    }
    public void gotoHome() {
        System.out.println("============gotoHome============");
    }
    public void gotoSchool() {
        System.out.println("============gotoSchool============");
    }
    public void oneday() {
        gotoHome();
        gotoSchool();
    }
    public final void onedayFinal() {
        gotoHome();
        gotoSchool();
    }
}

