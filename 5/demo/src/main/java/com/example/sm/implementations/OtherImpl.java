package com.example.sm.implementations;

import com.example.sm.interfaces.SomeInterface;

/**
 * Реализация интерфейса SomeInterface, выводящая "B" при вызове doSomething().
 */
public class OtherImpl implements SomeInterface {
    /**
     * Выводит "B" в консоль.
     */
    @Override
    public void doSomething() {
        System.out.println("B");
    }
}