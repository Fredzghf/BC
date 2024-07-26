package com.example.bc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bc.model.BinomialUiState

@Composable
fun ProbabilityResults(uiState: BinomialUiState) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProbabilityRow("P(X = ${uiState.x.ifEmpty { "x" }})", uiState.pXText)
        ProbabilityRow("P(X ≤ ${uiState.x.ifEmpty { "x" }})", uiState.pXLessOrEqualText)
        ProbabilityRow("P(X ≥ ${uiState.x.ifEmpty { "x" }})", uiState.pXGreaterOrEqualText)
        ProbabilityRow("P(X < ${uiState.x.ifEmpty { "x" }})", uiState.pXLessText)
        ProbabilityRow("P(X > ${uiState.x.ifEmpty { "x" }})", uiState.pXGreaterText)
    }
}

@Composable
fun ProbabilityRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )
    }
}