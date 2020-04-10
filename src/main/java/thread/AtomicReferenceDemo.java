package thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用
 */


class User {
    String username;
    int age;
    User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "username: " + username + "age: " + age;
    }
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {

        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString());
    }
}
