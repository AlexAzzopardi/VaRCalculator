package com.group.calculate;

public interface VaRCalculator {

	public double calculateVaR(double[] historicalValues, double confidenceLevel);
	public double calculateVaR(double[][] historicalValues, double confidenceLevel);
	
}
