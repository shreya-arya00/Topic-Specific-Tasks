package com.bobocode.basics;

/**
 * This demo demonstrates why using Object is not safe. It's not safe because runtime casting can cause runtime
 * exceptions. We should always fail as soon as possible. So in this code we should not allow setting String
 * value at compile time, if we expect to work with integers.
 * <p>
 * Refactored to use generic type "T" and demonstrate compile-time type safety.
 */
public class BoxDemoApp {
    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>(123);
        Box<Integer> intBox2 = new Box<>(321);
        System.out.println(intBox.getValue() + intBox2.getValue());

        intBox.setValue(222);
        // The following line will not compile, ensuring type safety
        // intBox.setValue("abc");

        // The following code will compile and run without issues
        System.out.println(intBox.getValue() + intBox2.getValue());
    }
}
