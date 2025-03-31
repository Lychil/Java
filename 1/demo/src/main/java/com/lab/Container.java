package com.lab;

import java.util.Objects;

public final class Container {
    private int[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    public Container() {
        data = new int[INITIAL_CAPACITY];
    }

    public Container(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Отрицательная емкость недопустима");
        data = new int[Math.min(capacity, MAX_CAPACITY)];
    }

    public void add(int value) {
        if (size == data.length) {
            resize();
        }
        data[size++] = value;
    }

    public int get(int index) {
        Objects.checkIndex(index, size);
        return data[index];
    }

    public int remove(int index) {
        Objects.checkIndex(index, size);
        int value = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return value;
    }

    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        data = new int[INITIAL_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        if (data.length == MAX_CAPACITY) {
            throw new OutOfMemoryError("Достигнута максимальная емкость контейнера");
        }
        int newCapacity = data.length + (data.length >> 1);
        newCapacity = Math.min(newCapacity, MAX_CAPACITY);
        int[] newData = new int[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}