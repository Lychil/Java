package com.example.core;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import com.example.annotations.AutoInjectable;

/**
 * Контейнер для внедрения зависимостей.
 * Осуществляет автоматическое внедрение реализаций в поля, помеченные
 * аннотацией @AutoInjectable,
 * на основе конфигурации из properties-файла.
 */
public class Injector {
    private Properties properties;

    /**
     * Создает экземпляр Injector с загрузкой конфигурации из указанного файла.
     * 
     * @param configFileName имя файла конфигурации в classpath
     * @throws RuntimeException если файл не найден или произошла ошибка загрузки
     */
    public Injector(String configFileName) {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("File " + configFileName + " not found in classpath");
            }
        } catch (IOException error) {
            throw new RuntimeException("Error loading settings file", error);
        }
    }

    /**
     * Внедряет зависимости в переданный объект.
     * 
     * @param object объект для внедрения зависимостей
     * @return объект с внедренными зависимостями
     * @param <T> тип объекта
     * @throws RuntimeException если не найдена реализация интерфейса или произошла
     *                          ошибка внедрения
     */
    public <T> T inject(T object) {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String implementationClassName = properties.getProperty(fieldType.getName());

                if (implementationClassName == null) {
                    throw new RuntimeException("No implementation found for interface " + fieldType.getName());
                }

                try {
                    Object implementationInstance = Class.forName(implementationClassName).getDeclaredConstructor()
                            .newInstance();
                    field.setAccessible(true);
                    field.set(object, implementationInstance);
                } catch (Exception error) {
                    throw new RuntimeException("Error injecting dependency", error);
                }
            }
        }

        return object;
    }
}