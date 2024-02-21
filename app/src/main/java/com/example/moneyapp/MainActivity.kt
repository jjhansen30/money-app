package com.example.moneyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moneyapp.ui.common.view.HintTextField
import com.example.moneyapp.ui.common.viewmodel.HintTextFieldViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainCalcViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val principleViewModel = HintTextFieldViewModel()
        val rateViewModel = HintTextFieldViewModel()
        val yearsViewModel = HintTextFieldViewModel()
        val compoundViewModel = HintTextFieldViewModel()

        setContent {

            MainCalculator(
                principleViewModel = principleViewModel,
                rateViewModel = rateViewModel,
                yearsViewModel = yearsViewModel,
                compoundViewModel = compoundViewModel
            )
        }
    }
}

@Composable
fun MainCalculator(
    mainViewModel: MainCalcViewModel = viewModel(),
    principleViewModel: HintTextFieldViewModel,
    rateViewModel: HintTextFieldViewModel,
    yearsViewModel: HintTextFieldViewModel,
    compoundViewModel: HintTextFieldViewModel
) {

    val imeActionNext = ImeAction.Next
    val imeActionDone = ImeAction.Done

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(8.dp)
    ) {
        HintTextField(
            label = stringResource(R.string.principle),
            imeActionHolder = imeActionNext,
            updatedValue = mainViewModel.principle,
            onValueChange = mainViewModel::onPrincipleChange,
            prefix = stringResource(R.string.dollar_sign),
            hintViewModel = principleViewModel
        )
        HintTextField(
            label = stringResource(R.string.rate),
            imeActionHolder = imeActionNext,
            updatedValue = mainViewModel.rate,
            onValueChange = mainViewModel::onRateChange,
            hintViewModel = rateViewModel
        )
        HintTextField(
            label = stringResource(R.string.years),
            imeActionHolder = imeActionNext,
            updatedValue = mainViewModel.years,
            onValueChange = mainViewModel::onYearsChange,
            hintViewModel = yearsViewModel
        )

        HintTextField(
            label = stringResource(R.string.compound_periods),
            imeActionHolder = imeActionDone,
            updatedValue = mainViewModel.compoundPeriods,
            onValueChange = mainViewModel::onCompoundChange,
            hintViewModel = compoundViewModel
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            text = mainViewModel.endBalance.value
        )
        Button(
            onClick = { mainViewModel.calculateEndBalance() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calculate", fontSize = 24.sp)
        }
    }
}