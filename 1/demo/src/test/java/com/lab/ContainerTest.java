package com.lab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Набор тестов для проверки функциональности класса Container.
 * Тесты покрывают все основные операции работы с контейнером:
 * - Добавление и получение элементов
 * - Автоматическое расширение при заполнении
 * - Удаление элементов
 * - Очистка контейнера
 * - Проверка наличия элементов
 * - Обработка ошибочных ситуаций
 */
public class ContainerTest {
    
    /**
     * Проверяет корректность добавления и последующего получения элементов.
     * Тест последовательно добавляет три элемента и проверяет,
     * что они сохраняются в правильном порядке.
     */
    @Test
    public void shouldAddAndRetrieveElements() {
        Container c = new Container();
        c.add(10);
        c.add(20);
        c.add(30);

        assertEquals(10, c.get(0));
        assertEquals(20, c.get(1));
        assertEquals(30, c.get(2));
    }

    /**
     * Проверяет автоматическое расширение контейнера при заполнении.
     * Добавляет больше элементов, чем начальная емкость контейнера,
     * и проверяет что все элементы были успешно добавлены.
     */
    @Test
    public void shouldExpandWhenFull() {
        Container c = new Container();
        for(int i = 0; i < 15; i++) {
            c.add(i);
        }

        assertEquals(15, c.size());
    }

    /**
     * Проверяет корректность отслеживания размера контейнера.
     * Проверяет что новый контейнер имеет размер 0 и что после добавления
     * элементов размер увеличивается.
     */
    @Test
    public void shouldTrackSize() {
        Container c = new Container();
        assertEquals(0, c.size());

        c.add(100);
        c.add(200);
        
        assertEquals(2, c.size());
    }

    /**
     * Проверяет корректность удаления элементов.
     * Проверяет что метод remove возвращает правильное значение
     * и размер контейнера уменьшается после удаления.
     */
    @Test
    public void shouldRemoveElements() {
        Container c = new Container();
        c.add(5);
        c.add(10);
        c.add(15);

        assertEquals(5, c.remove(0));
        assertEquals(2, c.size());
    }

    /**
     * Проверяет полную очистку контейнера.
     * Проверяет что после вызова clear размер становится равным 0
     * и контейнер возвращается в исходное состояние.
     */
    @Test
    public void shouldClearAllElements() {
        Container c = new Container();
        c.add(1);
        c.add(2);
        assertEquals(2, c.size());

        c.clear();
        assertEquals(0, c.size());
    }

    /**
     * Проверяет корректность проверки наличия элемента.
     * Проверяет что для существующих элементов возвращается true,
     * а для отсутствующих - false.
     */
    @Test
    public void shouldCheckElementPresence() {
        Container c = new Container();
        c.add(7);
        c.add(8);

        assertTrue(c.contains(7));
        assertFalse(c.contains(9));
    }

    /**
     * Проверяет обработку попытки удаления из пустого контейнера.
     * Ожидается выброс IndexOutOfBoundsException при попытке
     * удаления элемента из пустого контейнера.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldFailWhenRemovingFromEmpty() {
        Container c = new Container();
        c.remove(0);
    }

    /**
     * Проверяет обработку попытки доступа к пустому контейнеру.
     * Ожидается выброс IndexOutOfBoundsException при попытке
     * получения элемента из пустого контейнера.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldFailWhenAccessingEmpty() {
        Container c = new Container();
        c.get(0);
    }
}