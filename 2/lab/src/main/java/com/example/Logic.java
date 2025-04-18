package com.example;

public enum Logic {
    SIN("sin") {
        public double execute(double x) {
            return Math.sin(Math.toRadians(x));
        }
    },
    COS("cos") {
        public double execute(double x) {
            return Math.cos(Math.toRadians(x));
        }
    },
    SQRT("sqrt") {
        public double execute(double x) {
            if (x < 0) throw new IllegalArgumentException("Корень от отрицательного числа");
            return Math.sqrt(x);
        }
    };

    private final String funcName;

    private Logic(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncName() {
        return funcName;
    }

    public abstract double execute(double x);

    public static Logic getByName(String name) {
        for (Logic f : values()) {
            if (f.funcName.equals(name)) {
                return f;
            }
        }
        throw new IllegalArgumentException("Неизвестная функция: " + name);
    }
}