package com.example.moneyapp

import java.util.Locale
import kotlin.math.pow

data class MainCalcModel(
    var principle: Double,
    var rate: Float,
    var compoundPeriodsPerYear: Int,
    var numYears: Int,
    var contributingAmount: Double,
    var endBalance: Double
)

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
 * @return The future value of the investment as a Float.
 */
class InterestCalcModel (private val data: MainCalcModel) {

    fun calcEndBalance(): Double {
        val p = data.principle
        val n = data.compoundPeriodsPerYear
        val r = data.rate
        val t = data.numYears
        val base = 1 + (r / n)
        val exponent = n * t
        val result = p * base.pow(exponent)
        val formatted = String.format(Locale.US, "%.2f", result)
        val newResult = formatted.toDouble()
        data.endBalance = newResult

        return newResult
    }
}
