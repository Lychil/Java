package com.lab;

import java.util.Objects;

/**
 * Класс реализует динамический контейнер для хранения целочисленных значений.
 * Поддерживает основные операции добавления, удаления и поиска элементов.
 * Автоматически расширяется при заполнении.
 */
public final class Container {
    private int[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    /**
     * Создает новый контейнер с начальной емкостью по умолчанию.
     */
    public Container() {
        data = new int[INITIAL_CAPACITY];
    }

    /**
     * Создает новый контейнер с указанной начальной емкостью.
     *
     * @param capacity начальная емкость контейнера
     * @throws IllegalArgumentException если указана отрицательная емкость
     */
    public Container(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Отрицательная емкость недопустима");
        data = new int[Math.min(capacity, MAX_CAPACITY)];
    }

    /**
     * Добавляет новое значение в контейнер.
     * При необходимости автоматически увеличивает емкость контейнера.
     *
     * @param value добавляемое значение
     */
    public void add(int value) {
        if (size == data.length) {
            resize();
        }
        data[size++] = value;
    }

    /**
     * Возвращает значение по указанному индексу.
     *
     * @param index индекс запрашиваемого элемента
     * @return значение элемента
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    public int get(int index) {
        Objects.checkIndex(index, size);
        return data[index];
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param индекс индекс удаляемого элемента
     * @return значение удаленного элемента
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    public int remove(int index) {
        Objects.checkIndex(index, size);
        int value = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return value;
    }

    /**
     * Проверяет наличие значения в контейнере.
     *
     * @param value искомое значение
     * @return true если значение содержится в контейнере, false в противном случае
     */
    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Очищает контейнер, удаляя все элементы.
     * После очистки контейнер возвращается в начальное состояние.
     */
    public void clear() {
        data = new int[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Возвращает текущее количество элементов в контейнере.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Увеличивает емкость внутреннего массива при необходимости.
     * Новый размер рассчитывается как текущий размер + 50%.
     * 
     * @throws OutOfMemoryError при достижении максимально допустимой емкости
     */
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