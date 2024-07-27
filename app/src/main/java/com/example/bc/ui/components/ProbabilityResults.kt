package com.example.bc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bc.model.BinomialUiState

@Composable
fun ProbabilityResults(uiState: BinomialUiState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Labels column
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProbabilityLabel("P(X = ${uiState.x.ifEmpty { "x" }})")
            ProbabilityLabel("P(X ≤ ${uiState.x.ifEmpty { "x" }})")
            ProbabilityLabel("P(X ≥ ${uiState.x.ifEmpty { "x" }})")
            ProbabilityLabel("P(X < ${uiState.x.ifEmpty { "x" }})")
            ProbabilityLabel("P(X > ${uiState.x.ifEmpty { "x" }})")
        }

        // Values column
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProbabilityValue(uiState.pXText)
            ProbabilityValue(uiState.pXLessOrEqualText)
            ProbabilityValue(uiState.pXGreaterOrEqualText)
            ProbabilityValue(uiState.pXLessText)
            ProbabilityValue(uiState.pXGreaterText)
        }
    }
}

@Composable
fun ProbabilityLabel(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ProbabilityValue(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}