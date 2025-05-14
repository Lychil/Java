package com.example.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.Gender;

/**
 * Класс, представляющий сотрудника компании.
 */
public class Employee {
    private int id;
    private String name;
    private Gender gender;
    private Division division;
    private double salary;
    private Date birthDate;

    /**
     * Конструктор класса Employee.
     *
     * @param _id         ID сотрудника.
     * @param _name       полное имя сотрудника.
     * @param _gender     пол сотрудника.
     * @param _division   подразделение сотрудника.
     * @param _salary     зарплата сотрудника.
     * @param _birthDate  дата рождения сотрудника.
     */
    public Employee(int _id, String _name, Gender _gender, Division _division, double _salary, Date _birthDate) {
        this.id = _id;
        this.name = _name;
        this.gender = _gender;
        this.division = _division;
        this.salary = _salary;
        this.birthDate = _birthDate;
    }

    /**
     * Возвращает строковое представление сотрудника.
     *
     * @return строковое представление сотрудника.
     */
    @Override
    public String toString() {
        String formattedDate = birthDate != null
                ? new SimpleDateFormat("dd.MM.yyyy").format(birthDate)
                : "Не указана";

        return "Employee{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", gender = " + gender +
                ", division = " + division.toString() +
                ", salary = " + salary +
                ", birthDate = " + formattedDate +
                '}';
    }
}
