package com.example.moneyapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


class MainCalcViewModel: StateMachine() {
    private var dataBase: MainCalcModel = MainCalcModel(
        principle = 0.00,
        rate = 0f,
        compoundPeriodsPerYear = 0,
        numYears = 0,
        contributingAmount = 0.00,
        endBalance = 0.00
    )
    private var calcModel: InterestCalcModel = InterestCalcModel(dataBase)

    fun calcEndBalance() {
        dataBase.principle = principle.value.toDouble()
        dataBase.rate = rate.value.toFloat()
        dataBase.compoundPeriodsPerYear = compoundPeriods.value.toInt()
        dataBase.numYears = years.value.toInt()
        endBalance.value = calcModel.calcEndBalance().toString()
    }

    fun principleValueHandler(value: String) {
        principle.value = value
    }

    fun rateValueHandler(value: String) {
        rate.value = value
    }

    fun compoundPeriodsValueHandler(value: String) {
        compoundPeriods.value = value
    }

    fun yearsValueHandler(value: String) {
        years.value = value
    }

    fun contributingAmountValueHandler(value: String) {
        contributingAmount.value = value
    }
}

open class StateMachine {
    val principle: MutableState<String> = mutableStateOf("")
    val rate: MutableState<String> = mutableStateOf("")
    val compoundPeriods: MutableState<String> = mutableStateOf("")
    val years: MutableState<String> = mutableStateOf("")
    val contributingAmount: MutableState<String> = mutableStateOf("")
    val endBalance: MutableState<String> = mutableStateOf("")
}