package com.bobocode.basics;

/**
 * {@link Box} is a container class that can store a value of any given type. Using Object as a field type
 * is flexible, because we can store anything we want there. But it is not safe, because it requires runtime casting
 * and there is no guarantee that we know the type of the stored value.
 * <p>
 * Refactored to use generic type "T".
 *
 * @param <T> the type of the value to be stored in the box
 */
public class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
