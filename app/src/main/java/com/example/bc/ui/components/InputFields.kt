package com.example.bc.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.bc.R

@Composable
fun InputFields(
    probabilityText: String,
    numberOfTrials: String,
    numberOfSuccesses: String,
    onProbabilityChange: (String) -> Unit,
    onTrialsChange: (String) -> Unit,
    onSuccessesChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InputField(
            label = stringResource(R.string.probability),
            value = probabilityText,
            onValueChange = onProbabilityChange,
            keyboardType = KeyboardType.Decimal,
            modifier = Modifier.weight(1f)
        )
        InputField(
            label = stringResource(R.string.successes),
            value = numberOfSuccesses,
            onValueChange = onSuccessesChange,
            keyboardType = KeyboardType.Number,
            modifier = Modifier.weight(1f)
        )
        InputField(
            label = stringResource(R.string.trials),
            value = numberOfTrials,
            onValueChange = onTrialsChange,
            keyboardType = KeyboardType.Number,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f),
            textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp)
        )
    }
}