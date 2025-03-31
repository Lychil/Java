package com.lab;

public class Container {
    private int[] data;
    private int count;
    
    private static final int DEFAULT_SIZE = 10;

    public Container() {
        data = new int[DEFAULT_SIZE];
        count = 0;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public int get(int index) {
        validateIndex(index);
        return data[index];
    }

    public void add(int value) {
        if (count == data.length) {
            int[] newData = new int[data.length * 2];
            System.arraycopy(data, 0, newData, 0, count);
            data = newData;
        }
        data[count++] = value;
    }

    public int remove(int index) {
        validateIndex(index);
        int removed = data[index];
        System.arraycopy(data, index + 1, data, index, count - index - 1);
        count--;
        return removed;
    }

    public void clear() {
        data = new int[DEFAULT_SIZE];
        count = 0;
    }

    public boolean contains(int value) {
        for (int i = 0; i < count; i++) {
            if (data[i] == value) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return count;
    }
}