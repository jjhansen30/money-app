package com.example.moneyapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

lateinit var viewModel: MainCalcViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainCalcViewModel()
        setContent {
            MainCalculator(viewModel = viewModel)
        }
    }
}

@Composable
fun MainCalculator(viewModel: MainCalcViewModel) {
    val imeActionNext = ImeAction.Next
    val imeActionDone = ImeAction.Done
    val prefix = "$"
    val suffix = "%"
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(8.dp)
    ) {
        CommonTextField(
            viewModel = viewModel,
            label = stringResource(R.string.principle),
            imeActionHolder = imeActionNext,
            prefix = prefix,
            updatedValue = viewModel.principle.value,
            onValueChange = viewModel::principleValueHandler
        )
        CommonTextField(
            viewModel = viewModel,
            label = stringResource(R.string.rate),
            imeActionHolder = imeActionNext,
            suffix = suffix,
            updatedValue = viewModel.rate.value,
            onValueChange = viewModel::rateValueHandler,
            // Move Modifier to lower level
        )
        CommonTextField(
            viewModel = viewModel,
            label = stringResource(R.string.years),
            imeActionHolder = imeActionNext,
            updatedValue = viewModel.years.value,
            onValueChange = viewModel::yearsValueHandler,
        )
        CommonTextField(
            viewModel = viewModel,
            label = stringResource(R.string.compound_periods),
            imeActionHolder = imeActionDone,
            updatedValue = viewModel.compoundPeriods.value,
            onValueChange = viewModel::compoundPeriodsValueHandler,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            text = viewModel.endBalance.value
        )
        Button(
            onClick = { viewModel.calcEndBalance() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calculate", fontSize = 24.sp)
        }
    }
}

@Composable
fun CommonTextField(
    viewModel: MainCalcViewModel,
    label: String,
    imeActionHolder: ImeAction,
    prefix: String = "",
    suffix: String = "",
    updatedValue: String,
    onValueChange: (String) -> Unit
) {
    val customFontSize = 24
    val fontSizeMultiplier = 4.5f
    val minFocusCount = 1
    TextField(
        prefix = { Text(text = prefix, fontSize = customFontSize.sp) },
        suffix = { Text(text = suffix, fontSize = customFontSize.sp) },
        label = { Text(text = label, fontSize = customFontSize.sp) },
        textStyle = TextStyle.Default.copy(fontSize = customFontSize.sp),
        value = updatedValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(12.dp)
//            .height(customFontSize.dp * fontSizeMultiplier)
            .fillMaxWidth()
            .onFocusChanged { value ->
                viewModel.isFocused.value = value.isFocused
                if (viewModel.isFocused.value) {
                    viewModel.counter.value++
                    if (viewModel.counter.value >= minFocusCount) {
                        viewModel.unfocusedTextColor.value = Color.Black
                    }
                    if (viewModel.counter.value == minFocusCount) {
                        viewModel.clearTextField(label)
                    }
                } else { viewModel.counter.value = 0 }
            },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = viewModel.unfocusedTextColor.value
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal, imeAction = imeActionHolder
        )
    )
}
@Composable
@Preview
fun PreviewCalculator() {
    viewModel = MainCalcViewModel()
    MainCalculator(viewModel)
}