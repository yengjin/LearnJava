package juc;

import java.util.concurrent.TimeUnit;

class Phone {
    public synchronized static void sendEmail() throws Exception {
        // 暂停一会线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----sendEmail");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println("-----sendSMS");
    }

    public void hello() {
        System.out.println("-----hello");
    }
}

/**
 * 题目: 多线程8锁 (非常重要!!)
 * 1 标准访问: 请问先打印邮件还是短信? 邮件 - synchronized锁住了当前对象this, 同一时刻只能有一个线程操作
 * 2 邮件方法暂停4秒中, 先打印邮件还是短信?  邮件
 * 3 新增一个普通方法hello(), 请问先打印邮件还是hello ?   hello!  - hello普通方法不会争抢资源
 * 4 两部手机, 先打印邮件还是短信?  短信!  - 两个资源, 互不干扰
 * 5 两个**静态同步**方法, 同一部手机, 先打印邮件还是短信?   邮件  -  static方法上加同步锁, 锁的是Phone.class
 * 6 两个**静态同步**方法, 两部手机, 先打印邮件还是短信?  邮件   -  由于锁了整个类, 所以会被锁住
 * 7 1个静态同步方法, 1个普通同步方法,  1部手机, 先打印邮件还是短信?  短信!!  - 两个锁不一样(Phone.class, this)
 * 8 1个静态同步方法, 1个普通同步方法,  2部手机, 先打印邮件还是短信?  短信!!  - 同上
 *
 * 总结: synchronized 如果加在普通方法上, 锁的是**当前对象(this)**, 不干扰静态对象锁的获取
 *       synchronized 如果加在静态方法上, 锁的是**当前类(Phone.class)**
 */
public class Lock8 {

    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);  //保证A先启动
        new Thread(() -> {
            try {
                phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
