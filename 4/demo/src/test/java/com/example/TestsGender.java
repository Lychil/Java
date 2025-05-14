package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TestsGender {

    @Test
    void testGetGenderMale() {
        assertEquals(Gender.MALE, Gender.getGender("male"));
    }

    @Test
    void testGetGenderFemale() {
        assertEquals(Gender.FEMALE, Gender.getGender("female"));
    }

    @Test
    void testGetGenderCaseInsensitive() {
        assertEquals(Gender.FEMALE, Gender.getGender("FeMaLe"));
        assertEquals(Gender.MALE, Gender.getGender("MaLe"));
    }

    @Test
    void testGetGenderInvalidInput() {
        assertEquals(Gender.MALE, Gender.getGender("unknown"));
    }
}
