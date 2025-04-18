package com.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testSimpleAddition() {
        assertEquals(5.0, Calculator.calculate("2+3", new HashMap<>()), 0.0001);
    }

    @Test
    public void testExpressionWithParentheses() {
        assertEquals(20.0, Calculator.calculate("(2+3)*4", new HashMap<>()), 0.0001);
    }

    @Test
    public void testExpressionWithVariables() {
        Map<String, Double> vars = new HashMap<>();
        vars.put("x", 5.0);
        vars.put("y", 2.0);
        assertEquals(9.0, Calculator.calculate("x + y * 2", vars), 0.0001);
    }

    @Test
    public void testSinFunction() {
        assertEquals(1.0, Calculator.calculate("sin(90)", new HashMap<>()), 0.0001);
    }

    @Test
    public void testCosFunction() {
        assertEquals(0.0, Calculator.calculate("cos(90)", new HashMap<>()), 0.0001);
    }

    @Test
    public void testSqrtFunction() {
        assertEquals(4.0, Calculator.calculate("sqrt(16)", new HashMap<>()), 0.0001);
    }

    @Test
    public void testSqrtWithVariable() {
        Map<String, Double> vars = new HashMap<>();
        vars.put("a", 25.0);
        assertEquals(5.0, Calculator.calculate("sqrt(a)", vars), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnknownVariable() {
        Calculator.calculate("a + 1", new HashMap<>());
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        Calculator.calculate("10 / 0", new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidExpression() {
        Calculator.calculate("(2+3", new HashMap<>());
    }

    @Test
    public void testContainsFunction() {
        assertTrue(Calculator.containsFunction("sin"));
        assertFalse(Calculator.containsFunction("unknown"));
    }
}
