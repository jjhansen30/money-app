package com.example.moneyapp.ui.common.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color

class HintTextFieldViewModel {

    private var counter = INIT_COUNTER_VAL
    private val isFocused: MutableState<Boolean> = mutableStateOf(false)
    val unFocusedTextColor: MutableState<Color> = mutableStateOf(Color.LightGray)

    fun createHint(
        focusState: FocusState,
        mutableState: MutableState<String>
    ) {
        isFocused.value = focusState.isFocused
        if (isFocused.value) {
            counter++
            if (counter == MIN_FOCUS_COUNT) {
                mutableState.value = EMPTY_STRING
            }
        } else {
            if (counter >= MIN_FOCUS_COUNT) {
                unFocusedTextColor.value = Color.Black
            }
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val MIN_FOCUS_COUNT = 1
        private const val INIT_COUNTER_VAL = 0
    }
}