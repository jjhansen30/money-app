package com.example.moneyapp

import java.util.Locale
import kotlin.math.pow

interface Calculator {
    fun calculateInterest(
        principle: Double,
        rate: Double,
        years: Double,
        compoundPeriods: Double,
        contributingAmount: Double
    ): Double
}

/**
 * Calculates the future value of a lump sum investment based on compound interest.
 *
 * This class uses the compound interest formula: A = P(1 + r/n)^(nt)
 * where:
 * - P is the principal amount (float)
 * - r is the annual interest rate (float)
 * - n is the number of times that interest is compounded per year (int)
 * - t is the time the money is invested for in years (int)
 *
 * Note: Contributing amount is not accounted for yet because I don't know how
 * to implement it yet. Just assign it as 0.00 in the model.
 *
 * @return The future value of the investment as a Float.
 */
class CompoundInterestCalculator : Calculator {
    override fun calculateInterest(
        principle: Double,
        rate: Double,
        years: Double,
        compoundPeriods: Double,
        contributingAmount: Double
    ): Double {
        val base = 1 + (rate / compoundPeriods)
        val exponent = compoundPeriods * years
        val result = principle * base.pow(exponent)
        val formatted = String.format(Locale.US, "%.2f", result)
        val endBalance = formatted.toDouble()

        return endBalance
    }
}

/**
 * Not Implemented Yet
 */
class CompoundInterestCalculatorContributing : Calculator {
    override fun calculateInterest(
        principle: Double,
        rate: Double,
        years: Double,
        compoundPeriods: Double,
        contributingAmount: Double
    ): Double {
        TODO("Not yet implemented")
    }
}
