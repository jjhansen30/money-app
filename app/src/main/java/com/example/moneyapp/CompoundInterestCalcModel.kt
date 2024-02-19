package com.example.moneyapp

class CompoundInterestCalcModel(
    private val compoundInterestCalculator: CompoundInterestCalculator
) {
    fun calculateInterest(
        principle: Double,
        rate: Double,
        years: Double,
        compoundPeriods: Double,
        contributingAmount: Double = 0.00
    ): Double {
        return compoundInterestCalculator.calculateInterest(
            principle,
            rate,
            years,
            compoundPeriods,
            contributingAmount
        )
    }
}