package com.example.bc.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bc.ui.components.CalculateButton
import com.example.bc.ui.components.DisplayOptions
import com.example.bc.ui.components.InputFields
import com.example.bc.ui.components.ProbabilityResults
import com.example.bc.utils.ResourceManager
import com.example.bc.viewmodel.BinomialViewModel
import com.example.bc.viewmodel.BinomialViewModelFactory

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val resourceManager = remember { ResourceManager(context) }
    val viewModelFactory = remember { BinomialViewModelFactory(resourceManager) }
    val viewModel: BinomialViewModel = viewModel(factory = viewModelFactory)

    val uiState by viewModel.uiState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Display error toast when errorMessage is not null
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            viewModel.clearErrorMessage()
        }
    }

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