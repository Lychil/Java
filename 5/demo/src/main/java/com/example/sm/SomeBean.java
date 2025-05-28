package com.example.sm;

import com.example.annotations.AutoInjectable;
import com.example.sm.interfaces.SomeInterface;
import com.example.sm.interfaces.SomeOtherInterface;

/**
 * Класс, демонстрирующий внедрение зависимостей.
 * Содержит поля для внедрения реализаций интерфейсов.
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Вызывает методы внедренных зависимостей.
     * Выводит результат их работы в консоль.
     */
    public void res() {
        field1.doSomething();
        field2.doSomeOther();
    }
}