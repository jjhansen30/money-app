package com.example.moneyapp

import android.content.res.Resources
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat.getString


class MainCalcViewModel : StateMachine() {
    private var dataBase: MainCalcModel = MainCalcModel(
        principle = 0.00,
        rate = 0f,
        compoundPeriodsPerYear = 0,
        numYears = 0,
        contributingAmount = 0.00,
        endBalance = 0.00
    )
    private var calcModel: InterestCalcModel = InterestCalcModel(dataBase)
    private val prefix = "$"
    private val suffix = "%"

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

    fun clearTextField(field: String) {
        when (field) {
            "Principle" -> principle.value = ""
            "Rate" -> rate.value = ""
            "Years" -> years.value = ""
            "Compounding Periods" -> compoundPeriods.value = ""
            "Contributing Amount" -> contributingAmount.value = ""
        }
    }
}

open class StateMachine {
    val principle: MutableState<String> = mutableStateOf("0.00")
    val rate: MutableState<String> = mutableStateOf("0")
    val compoundPeriods: MutableState<String> = mutableStateOf("0")
    val years: MutableState<String> = mutableStateOf("0")
    val contributingAmount: MutableState<String> = mutableStateOf("0.00")
    val endBalance: MutableState<String> = mutableStateOf("0.00")
    val counter: MutableState<Int> = mutableIntStateOf(0)
    val isFocused: MutableState<Boolean> = mutableStateOf(false)
    val unfocusedTextColor: MutableState<Color> = mutableStateOf(Color.LightGray)
}