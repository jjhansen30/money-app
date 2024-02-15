package com.example.moneyapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainCalculator(viewModel: MainCalcViewModel) {
    val imeActionNext = ImeAction.Next
    val imeActionDone = ImeAction.Done

    Column (
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(8.dp)
    ){
        CommonTextField(
            label = stringResource(R.string.principle),
            viewModel = viewModel,
            imeActionHolder = imeActionNext,
            updatedValue = viewModel.principle.value,
            onValueChange = viewModel::principleValueHandler
        )
        CommonTextField(
            label = stringResource(R.string.rate),
            viewModel = viewModel,
            imeActionHolder = imeActionNext,
            updatedValue = viewModel.rate.value,
            onValueChange = viewModel::rateValueHandler
        )
        CommonTextField(
            label = stringResource(R.string.years),
            viewModel = viewModel,
            imeActionHolder = imeActionNext,
            updatedValue = viewModel.years.value,
            onValueChange = viewModel::yearsValueHandler
        )
        CommonTextField(
            label = stringResource(R.string.compound_periods),
            viewModel = viewModel,
            imeActionHolder = imeActionDone,
            updatedValue = viewModel.compoundPeriods.value,
            onValueChange = viewModel::compoundPeriodsValueHandler
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
    label: String,
    viewModel: MainCalcViewModel,
    imeActionHolder: ImeAction,
    updatedValue: String,
    onValueChange: (String) -> Unit
) {
    val customFontSize = 24
    val fontSizeMultiplier = 4
    TextField(
        modifier = Modifier
            .padding(12.dp)
//            .height(customFontSize.dp * fontSizeMultiplier)
            .fillMaxWidth()
            .background(color = Color.Gray),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = imeActionHolder),
        label = { Text(text = label, fontSize = customFontSize.sp) },
        textStyle = TextStyle.Default.copy(fontSize = customFontSize.sp),
        value = updatedValue,
        onValueChange = onValueChange
    )
}

@Composable
@Preview
fun PreviewCalculator() {
    viewModel = MainCalcViewModel()
    MainCalculator(viewModel)
}