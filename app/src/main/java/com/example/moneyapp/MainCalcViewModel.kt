package com.example.moneyapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainCalcViewModel @Inject constructor(
    private val compoundCalculatorModel: CompoundCalculatorModel
) : ViewModel() {

    val principle: MutableState<String> = mutableStateOf(STR_DOLLAR)
    val rate: MutableState<String> = mutableStateOf(STR_ZERO)
    val compoundPeriods: MutableState<String> = mutableStateOf(STR_ZERO)
    val years: MutableState<String> = mutableStateOf(STR_ZERO)
    val contributingAmount: MutableState<String> = mutableStateOf(STR_DOLLAR) // TODO()
    val endBalance: MutableState<String> = mutableStateOf(STR_DOLLAR)

    fun calculateEndBalance() {
        val balance = compoundCalculatorModel.calculateInterest(
            principle = principle.value.toDouble(),
            rate = rate.value.toDouble(),
            years = years.value.toDouble(),
            compoundPeriods = compoundPeriods.value.toDouble(),
            contributingAmount = 0.00
        )
        endBalance.value = balance.toString()
    }

    fun onPrincipleChange(value: String) {
        principle.value = value
    }

    fun onRateChange(value: String) {
        val charPercent = "%"
        val newValue = value.replace(charPercent, "")

        rate.value = newValue
    }

    fun onCompoundChange(value: String) {
        compoundPeriods.value = value
    }

    fun onYearsChange(value: String) {
        years.value = value
    }

    companion object {
        const val STR_ZERO = "0"
        const val STR_DOLLAR = "0.00"
    }
}