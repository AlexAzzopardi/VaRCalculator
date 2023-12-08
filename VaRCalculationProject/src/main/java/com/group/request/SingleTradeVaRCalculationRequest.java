package com.group.request;

import lombok.Data;

@Data
public class SingleTradeVaRCalculationRequest {

    private double[] historicalValues;
    private double confidenceLevel;

}
