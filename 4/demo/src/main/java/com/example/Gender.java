package com.example;

/**
 * Перечисление для обозначения пола сотрудника.
 */
public enum Gender {
    MALE,
    FEMALE;

    /**
     * Получает значение пола по строковому представлению.
     *
     * @param gender строковое представление пола (например, "male" или "female").
     * @return значение enum Gender.
     */
    public static Gender getGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
                return MALE;
            case "female":
                return FEMALE;
            default:
                return MALE;
        }
    }
}
