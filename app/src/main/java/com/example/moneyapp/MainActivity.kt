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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyapp.ui.common.view.HintTextField
import com.example.moneyapp.ui.common.viewmodel.HintTextFieldViewModel

lateinit var viewModel: MainCalcViewModel
lateinit var principleViewModel: HintTextFieldViewModel
lateinit var rateViewModel: HintTextFieldViewModel
lateinit var yearsViewModel: HintTextFieldViewModel
lateinit var compoundViewModel: HintTextFieldViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainCalcViewModel()
        principleViewModel = HintTextFieldViewModel()
        rateViewModel = HintTextFieldViewModel()
        yearsViewModel = HintTextFieldViewModel()
        compoundViewModel = HintTextFieldViewModel()

        setContent {
            MainCalculator(
                viewModel = viewModel,
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
    viewModel: MainCalcViewModel,
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
            updatedValue = viewModel.principle,
            onValueChange = viewModel::onPrincipleChange,
            prefix = viewModel.prefix,
            viewModel = principleViewModel
        )
        HintTextField(
            label = stringResource(R.string.rate),
            imeActionHolder = imeActionNext,
            updatedValue = viewModel.rate,
            onValueChange = viewModel::onRateChange,
            suffix = viewModel.suffix,
            viewModel = rateViewModel
        )
        HintTextField(
            label = stringResource(R.string.years),
            imeActionHolder = imeActionNext,
            updatedValue = viewModel.years,
            onValueChange = viewModel::onYearsChange,
            viewModel = yearsViewModel
        )

        HintTextField(
            label = stringResource(R.string.compound_periods),
            imeActionHolder = imeActionDone,
            updatedValue = viewModel.compoundPeriods,
            onValueChange = viewModel::onCompoundChange,
            viewModel = compoundViewModel
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