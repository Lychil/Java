package com.example;

import com.example.core.Injector;
import com.example.sm.SomeBean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для проверки поведения Injector и запуска App.
 */
public class InjectorTestSuite {

    /**
     * Тестирует поведение конструктора Injector при передаче null в качестве параметра.
     */
    @Test
    public void testNullParamConstructor() {
        assertThrows(Exception.class, () -> new Injector(null));
    }

    /**
     * Тестирует поведение метода inject при передаче null.
     */
    @Test
    public void testNullObject() {
        Injector injector = new Injector("injector-ac.properties");

        assertThrows(Exception.class, () -> injector.inject(null));
    }

    /**
     * Тестирует внедрение зависимостей с первой конфигурацией.
     */
    @Test
    public void testFirstFileProperties() {
        Injector injector = new Injector("injector-ac.properties");
        injector.inject(new SomeBean()).res();

        assertDoesNotThrow(() -> App.main(new String[] {}));
    }

    /**
     * Тестирует внедрение зависимостей с альтернативной конфигурацией.
     */
    @Test
    public void testSecondFileProperties() {
        Injector injector = new Injector("injector-bc.properties");
        injector.inject(new SomeBean()).res();

        assertDoesNotThrow(() -> App.main(new String[] {}));
    }

    /**
     * Проверяет вызов метода foo() после внедрения зависимостей.
     */
    @Test
    public void testOtherInjectorProperties() {
        Injector injector = new Injector("injector-bc.properties");
        SomeBean sb = injector.inject(new SomeBean());

        assertDoesNotThrow(sb::res);
    }

    /**
     * Проверяет повторную инъекцию на том же объекте.
     */
    @Test
    public void testDoubleInjection() {
        Injector injector = new Injector("injector-ac.properties");
        SomeBean sb = new SomeBean();
        injector.inject(sb);

        assertDoesNotThrow(() -> injector.inject(sb));
    }
}
