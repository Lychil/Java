package com.lab;

import java.util.Scanner;

/**
 * Основной класс приложения для демонстрации работы с контейнером чисел.
 * Реализует интерактивное консольное меню с возможностью выполнения следующих операций:
 * 
 * Добавление новых элементов
 * Удаление элементов по индексу
 * Получение элементов по индексу
 * Проверка наличия элементов
 * Очистка контейнера
 */
public class Main {

    /**
     * Точка входа в приложение.
     * Создает экземпляр контейнера и запускает интерактивное меню.
     * Обрабатывает пользовательский ввод и выполняет соответствующие операции
     * до получения команды на завершение работы.
     * 
     * @param args аргументы командной строки (не используются в данной реализации)
     * 
     * @see Container
     */
    public static void main(String[] args) {
        Container container = new Container();
        Scanner in = new Scanner(System.in);
        boolean running = true;
        int num, index, choice;

        while (running) {
            System.out.println("\n1 - Добавить\n2 - Удалить\n3 - Получить\n4 - Размер\n5 - Проверить наличие\n6 - Очистить\n0 - Выход");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Введите число:");
                    num = in.nextInt();
                    container.add(num);
                    break;
                case 2:
                    System.out.println("Введите индекс:");
                    index = in.nextInt();
                    int removed = container.remove(index);
                    System.out.println("Удалено: " + removed);
                    break;
                case 3:
                    System.out.println("Введите индекс:");
                    index = in.nextInt();
                    int value = container.get(index);
                    System.out.println("Значение: " + value);
                    break;
                case 4:
                    System.out.println("Размер: " + container.size());
                    break;
                case 5:
                    System.out.println("Введите элемент:");
                    num = in.nextInt();
                    System.out.println(container.contains(num) ? "Элемент найден" : "Элемент отсутствует");
                    break;
                case 6:
                    container.clear();
                    System.out.println("Контейнер очищен");
                    break;
                case 0:
                    System.out.println("Завершение работы...");
                    running = false;
                    break;
                default:
                    System.out.println("Неверная команда");
            }
        }

        in.close();
    }
}