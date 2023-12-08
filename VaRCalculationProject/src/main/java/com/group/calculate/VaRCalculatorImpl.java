package com.group.calculate;

import java.util.Arrays;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * A component for calculating Value at Risk (VaR) for single trades and portfolios.
 */
@Primary
@Component
public class VaRCalculatorImpl implements VaRCalculator{
	
    /**
     * Calculates the VaR for a single trade based on historical values and a confidence level.
     *
     * @param  historicalValues - An array of historical values for the single trade.
     * @param  confidenceLevel  - The confidence level for calculating VaR (e.g. 0.95 for 95% confidence).
     * @return The VaR for the single trade.
     * @throws IllegalArgumentException If historicalValues is null or empty.
     * @throws IllegalStateException    If an invalid index is calculated for VaR.
     */
	@Override
    public double calculateVaR(double[] historicalValues, double confidenceLevel) {
        if (historicalValues == null || historicalValues.length == 0) {
            throw new IllegalArgumentException("Historical values must not be null or empty.");
        }

        Arrays.sort(historicalValues);
        int index = (int) Math.ceil((1 - confidenceLevel) * historicalValues.length);

        if (index <= 0 || index > historicalValues.length) {
            throw new IllegalStateException("Invalid index calculated for VaR.");
        }

        return -historicalValues[index - 1];
    }

    /**
     * Calculates the VaR for a portfolio based on historical values and a confidence level.
     *
     * @param  historicalValues - An array of arrays representing historical values for each trade in the portfolio.
     * @param  confidenceLevel  - The confidence level for calculating VaR (e.g. 0.95 for 95% confidence).
     * @return The VaR for the portfolio.
     * @throws IllegalArgumentException If historicalValues is null, empty, or contains invalid trade values or trades or not of same length.
     */
	@Override
    public double calculateVaR(double[][] historicalValues, double confidenceLevel) {
        if (historicalValues == null || historicalValues.length == 0 || historicalValues[0].length == 0) {
            throw new IllegalArgumentException("Historical values for the portfolio must not be null or empty.");
        } if (Arrays.stream(historicalValues)
                .allMatch(tradeValues -> tradeValues != null && tradeValues.length == historicalValues[0].length) == false) {
        	throw new IllegalArgumentException("Arrays in historicalValues must have the same length.");
        }
        	  
        double[] portfolioPnL = Arrays.stream(historicalValues)
                .reduce(new double[historicalValues[0].length], (partialSum, tradeValues) -> {
                            for (int i = 0; i < tradeValues.length; i++) {
                                partialSum[i] += tradeValues[i];
                            }
                            return partialSum;
                        });
        
        return calculateVaR(portfolioPnL, confidenceLevel);
    }
}
