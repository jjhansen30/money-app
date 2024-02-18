package com.example.moneyapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

open class StateMachine {
    val principle: MutableState<String> = mutableStateOf(STR_INIT_DOLLAR)
    val rate: MutableState<String> = mutableStateOf(STR_ZERO)
    val compoundPeriods: MutableState<String> = mutableStateOf(STR_ZERO)
    val years: MutableState<String> = mutableStateOf(STR_ZERO)
    val contributingAmount: MutableState<String> = mutableStateOf(STR_INIT_DOLLAR)
    val endBalance: MutableState<String> = mutableStateOf(STR_INIT_DOLLAR)

    companion object {
        const val STR_ZERO = "0"
        const val STR_INIT_DOLLAR = "0.00"
        const val ZERO = 0
        const val INIT_DOLLAR = 0.00
        const val PREFIX = "$"
        const val SUFFIX = "%"
    }
}

class MainCalcViewModel : StateMachine() {
    private var dataBase: MainCalcModel = MainCalcModel(
        principle = INIT_DOLLAR,
        rate = 0f,
        compoundPeriodsPerYear = ZERO,
        numYears = ZERO,
        contributingAmount = INIT_DOLLAR,
        endBalance = INIT_DOLLAR
    )
    private var calcModel: InterestCalcModel = InterestCalcModel(dataBase)
    val prefix = PREFIX
    val suffix = SUFFIX

    fun calcEndBalance() {
        dataBase.principle = principle.value.toDouble()
        dataBase.rate = rate.value.toFloat()
        dataBase.compoundPeriodsPerYear = compoundPeriods.value.toInt()
        dataBase.numYears = years.value.toInt()
        endBalance.value = calcModel.calcEndBalance().toString()
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
}