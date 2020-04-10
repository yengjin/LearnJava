package jvm;

public class CircularReference {

    CircularReference reference = null;

    public static void main(String[] args) {

        CircularReference reference1 = new CircularReference(); // 1-1
        CircularReference reference2 = new CircularReference(); // 2-1

        reference1.reference = reference2;  // 2-2
        reference2.reference = reference1;  // 1-2

        reference1 = null;
        reference2 = null;

        System.gc();
    }
}
