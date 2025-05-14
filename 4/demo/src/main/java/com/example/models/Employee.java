package com.example.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.Gender;

public class Employee {
    private int id;
    private String name;
    private Gender gender;
    private Division division;
    private double salary;
    private Date birthDate;

    public Employee(int _id, String _name, Gender _gender, Division _division, double _salary, Date _birthDate) {
        this.id = _id;
        this.name = _name;
        this.gender = _gender;
        this.division = _division;
        this.salary = _salary;
        this.birthDate = _birthDate;
    }

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
