package com.group.calculate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

@Component 
public class VaRCalculatorTest {

    @InjectMocks
    private VaRCalculatorImpl vaRCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateVaR_SingleTrade_SuccessfulExecution() {
        double[] historicalValues = {1.0, 2.0, 3.0};
        double confidenceLevel = 0.95;

        double result = vaRCalculator.calculateVaR(historicalValues, confidenceLevel);
        assertEquals(-1.0, result);
    }

    @Test
    void calculateVaR_SingleTrade_NullValues_ExceptionThrown() {
        double[] historicalValues = null;
        double confidenceLevel = 0.95;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> vaRCalculator.calculateVaR(historicalValues, confidenceLevel));
        assertEquals("Historical values must not be null or empty.", exception.getMessage());
    }

    @Test
    void calculateVaR_SingleTrade_EmptyValues_ExceptionThrown() {
        double[] historicalValues = {};
        double confidenceLevel = 0.95;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> vaRCalculator.calculateVaR(historicalValues, confidenceLevel));
        assertEquals("Historical values must not be null or empty.", exception.getMessage());
    }

    @Test
    void calculateVaR_SingleTrade_InvalidIndex_ExceptionThrown() {
        double[] historicalValues = {1.0, 2.0, 3.0};
        double confidenceLevel = 1.5;

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> vaRCalculator.calculateVaR(historicalValues, confidenceLevel));
        assertEquals("Invalid index calculated for VaR.", exception.getMessage());
    }

    @Test
    void calculateVaR_Portfolio_SuccessfulExecution() {
        double[][] historicalValues = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        double confidenceLevel = 0.95;

        double result = vaRCalculator.calculateVaR(historicalValues, confidenceLevel);
        assertEquals(-5.0, result);
    }

    @Test
    void calculateVaR_Portfolio_NullValues_ExceptionThrown() {
        double[][] historicalValues = null;
        double confidenceLevel = 0.95;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> vaRCalculator.calculateVaR(historicalValues, confidenceLevel));
        assertEquals("Historical values for the portfolio must not be null or empty.", exception.getMessage());
    }

    @Test
    void calculateVaR_Portfolio_EmptyValues_ExceptionThrown() {
        double[][] historicalValues = {};
        double confidenceLevel = 0.95;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> vaRCalculator.calculateVaR(historicalValues, confidenceLevel));
        assertEquals("Historical values for the portfolio must not be null or empty.", exception.getMessage());
    }

    @Test
    void calculateVaR_Portfolio_InvalidTradeValues_ExceptionThrown() {
        double[][] historicalValues = {{1.0, 2.0, 3.0}, {4.0, 5.0}};
        double confidenceLevel = 0.95;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> vaRCalculator.calculateVaR(historicalValues, confidenceLevel));
        assertEquals("Arrays in historicalValues must have the same length.", exception.getMessage());
    }
}