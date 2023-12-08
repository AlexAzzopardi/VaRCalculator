package com.group.calculate;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class HistoricalDataGenerator {

    private static final int NUM_DAYS = 252;

    public double[] generateRandomSingleTradeValues() {
        Random random = new Random();
        double[] values = new double[NUM_DAYS];
        for (int i = 0; i < NUM_DAYS; i++) {
            values[i] = random.nextGaussian() * 100000;
        }
        return values;
    }

    public double[][] generateRandomPortfolioValues(int numTrades) {
        double[][] values = new double[numTrades][NUM_DAYS];
        for (int trade = 0; trade < numTrades; trade++) {
            values[trade] = generateRandomSingleTradeValues();
        }
        return values; 
    }
}
