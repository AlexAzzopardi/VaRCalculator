package com.group.request;

import lombok.Data;

@Data
public class PortfolioVaRCalculationRequest {

    private double[][] historicalValues;
    private double confidenceLevel;

}
