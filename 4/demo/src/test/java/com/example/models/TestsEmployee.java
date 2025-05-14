package com.example.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.Gender;

class TestsEmployee {

    @Test
    void testEmployeeCreation() throws ParseException {
        Division division = new Division("HR");
        Date birthDate = new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1990");
        Employee emp = new Employee(1, "Alice", Gender.FEMALE, division, 5000.0, birthDate);

        assertNotNull(emp.toString());
        assertTrue(emp.toString().contains("Alice"));
        assertTrue(emp.toString().contains("HR"));
        assertTrue(emp.toString().contains("5000.0"));
    }
}
