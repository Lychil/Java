package com.example.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TestsDivision {

    @Test
    void testDivisionCreation() {
        Division division = new Division("IT");
        assertNotNull(division.toString());
        assertTrue(division.toString().contains("IT"));
    }
}
