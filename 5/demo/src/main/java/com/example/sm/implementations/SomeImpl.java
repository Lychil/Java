package com.example.sm.implementations;

import com.example.sm.interfaces.SomeInterface;

/**
 * Реализация интерфейса SomeInterface, выводящая "A" при вызове doSomething().
 */
public class SomeImpl implements SomeInterface {
    /**
     * Выводит "A" в консоль.
     */
    @Override
    public void doSomething() {
        System.out.println("A");
    }
}