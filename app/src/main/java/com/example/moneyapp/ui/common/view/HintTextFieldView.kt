package com.example.moneyapp.ui.common.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyapp.R
import com.example.moneyapp.ui.common.viewmodel.HintTextFieldViewModel


@Composable
fun HintTextField(
    hintViewModel: HintTextFieldViewModel,
    label: String,
    imeActionHolder: ImeAction,
    updatedValue: MutableState<String>,
    onValueChange: (String) -> Unit,
    prefix: String = "",
    suffix: String = "",
    fontSize: TextUnit = 24.sp,
    padding: Dp = 12.dp
) {

    TextField(
        prefix = { Text(text = prefix, fontSize = fontSize) },
        label = {
            Text(
                text = label,
                fontSize = fontSize,
                lineHeight = 28.sp,
                maxLines = 2
            )
        },
        textStyle = TextStyle.Default.copy(
            fontSize = fontSize
        ),
        value = updatedValue.value + suffix,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .onFocusChanged { focusState -> hintViewModel.createHint(focusState, updatedValue) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = hintViewModel.unFocusedTextColor.value,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = imeActionHolder
        )
    )
}

