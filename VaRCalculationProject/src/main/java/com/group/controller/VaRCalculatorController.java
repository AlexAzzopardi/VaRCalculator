package com.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.calculate.VaRCalculator;
import com.group.request.PortfolioVaRCalculationRequest;
import com.group.request.SingleTradeVaRCalculationRequest;

@RestController
@RequestMapping("/var")
public class VaRCalculatorController {

    private final VaRCalculator varCalculator;

    @Autowired
    public VaRCalculatorController(VaRCalculator varCalculator) {
        this.varCalculator = varCalculator;
    }

    @PostMapping("/single-trade")
    public double calculateVaRSingleTrade(@RequestBody SingleTradeVaRCalculationRequest request) {
        return varCalculator.calculateVaR(request.getHistoricalValues(), request.getConfidenceLevel());
    }

    @PostMapping("/portfolio")
    public double calculateVaRPortfolio(@RequestBody PortfolioVaRCalculationRequest request) {
        return varCalculator.calculateVaR(request.getHistoricalValues(), request.getConfidenceLevel());
    }
}
