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


//
///**
// * Not Implemented Yet
// */
//class CompoundInterestCalculatorContributing : Calculator {
//    override fun calculateInterest(
//        principle: Double,
//        rate: Double,
//        years: Double,
//        compoundPeriods: Double,
//        contributingAmount: Double
//    ): Double {
//        TODO("Not yet implemented")
//    }
//}
