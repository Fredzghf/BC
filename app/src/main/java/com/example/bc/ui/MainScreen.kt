package com.example.bc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bc.ui.components.CalculateButton
import com.example.bc.ui.components.DisplayOptions
import com.example.bc.ui.components.InputFields
import com.example.bc.ui.components.ProbabilityResults
import com.example.bc.viewmodel.BinomialViewModel

@Composable
fun MainScreen(viewModel: BinomialViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputFields(
            probabilityText = uiState.probabilityText,
            numberOfTrials = uiState.numberOfTrials,
            numberOfSuccesses = uiState.numberOfSuccesses,
            onProbabilityChange = viewModel::updateProbability,
            onTrialsChange = viewModel::updateTrials,
            onSuccessesChange = viewModel::updateSuccesses
        )

        CalculateButton(onClick = viewModel::calculateProbabilities)

        ProbabilityResults(uiState = uiState)

        DisplayOptions(
            isPercentage = uiState.isPercentage,
            isFullPrecision = uiState.isFullPrecision,
            onPercentageChange = viewModel::togglePercentage,
            onFullPrecisionChange = viewModel::toggleFullPrecision
        )
    }
}