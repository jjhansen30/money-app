package com.example.moneyapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class MainCalcViewModel {
    private var interestCalculator: CompoundInterestCalculator = CompoundInterestCalculator()
    private var interestCalcModel = CompoundInterestCalcModel(interestCalculator)

    val principle: MutableState<String> = mutableStateOf(STR_INIT_DOLLAR)
    val rate: MutableState<String> = mutableStateOf("$STR_ZERO%")
    val compoundPeriods: MutableState<String> = mutableStateOf(STR_ZERO)
    val years: MutableState<String> = mutableStateOf(STR_ZERO)
    val contributingAmount: MutableState<String> = mutableStateOf(STR_INIT_DOLLAR) // TODO()
    val endBalance: MutableState<String> = mutableStateOf(STR_INIT_DOLLAR)

    fun calculateEndBalance() {
        val balance = interestCalcModel.calculateInterest(
            principle = principle.value.toDouble(),
            rate = rate.value.toDouble(),
            years = years.value.toDouble(),
            compoundPeriods = compoundPeriods.value.toDouble()
        )
        endBalance.value = balance.toString()
    }

    fun onPrincipleChange(value: String) {
        principle.value = value
    }

    fun onRateChange(value: String) {
        rate.value = value
    }

    fun onCompoundChange(value: String) {
        compoundPeriods.value = value
    }

    fun onYearsChange(value: String) {
        years.value = value
    }

    companion object {
        const val STR_ZERO = "0"
        const val STR_INIT_DOLLAR = "0.00"
    }
}