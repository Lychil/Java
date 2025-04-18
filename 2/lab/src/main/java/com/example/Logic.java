package com.example;

/**
 * Перечисление {@code Logic} определяет набор математических функций, которые могут быть выполнены в выражениях.
 * Каждая функция имеет имя и может быть использована в математических выражениях для выполнения соответствующих операций.
 * 
 * Доступные функции:
 * <ul>
 *     <li>{@link #SIN} - синус</li>
 *     <li>{@link #COS} - косинус</li>
 *     <li>{@link #SQRT} - квадратный корень</li>
 * </ul>
 */
public enum Logic {
    
    /**
     * Функция для вычисления синуса угла (в градусах).
     */
    SIN("sin") {
        /**
         * Выполняет вычисление синуса угла, заданного в градусах.
         * 
         * @param x угол в градусах
         * @return синус угла
         */
        public double execute(double x) {
            return Math.sin(Math.toRadians(x));
        }
    },

    /**
     * Функция для вычисления косинуса угла (в градусах).
     */
    COS("cos") {
        /**
         * Выполняет вычисление косинуса угла, заданного в градусах.
         * 
         * @param x угол в градусах
         * @return косинус угла
         */
        public double execute(double x) {
            return Math.cos(Math.toRadians(x));
        }
    },

    /**
     * Функция для вычисления квадратного корня.
     */
    SQRT("sqrt") {
        /**
         * Выполняет вычисление квадратного корня.
         * Если аргумент отрицателен, выбрасывает исключение {@link IllegalArgumentException}.
         * 
         * @param x число, из которого нужно извлечь корень
         * @return квадратный корень из {@code x}
         * @throws IllegalArgumentException если {@code x} отрицательное
         */
        public double execute(double x) {
            if (x < 0) throw new IllegalArgumentException("Корень от отрицательного числа");
            return Math.sqrt(x);
        }
    };

    private final String funcName;

    /**
     * Конструктор для инициализации имени функции.
     * 
     * @param funcName имя функции
     */
    private Logic(String funcName) {
        this.funcName = funcName;
    }

    /**
     * Возвращает имя функции.
     * 
     * @return имя функции
     */
    public String getFuncName() {
        return funcName;
    }

    /**
     * Выполняет операцию, связанную с функцией.
     * Каждая функция имеет свою собственную реализацию вычисления.
     * 
     * @param x аргумент функции
     * @return результат вычисления
     */
    public abstract double execute(double x);

    /**
     * Получает функцию по имени.
     * 
     * @param name имя функции
     * @return соответствующая функция {@code Logic}
     * @throws IllegalArgumentException если функция с таким именем не найдена
     */
    public static Logic getByName(String name) {
        for (Logic f : values()) {
            if (f.funcName.equals(name)) {
                return f;
            }
        }
        throw new IllegalArgumentException("Неизвестная функция: " + name);
    }
}
