package com.example.bc.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bc.model.BinomialDistribution
import com.example.bc.model.BinomialUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BinomialViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BinomialUiState())
    val uiState: StateFlow<BinomialUiState> = _uiState.asStateFlow()

    fun updateProbability(value: String) {
        _uiState.update { it.copy(probabilityText = value) }
    }

    fun updateTrials(value: String) {
        _uiState.update { it.copy(numberOfTrials = value) }
    }

    fun updateSuccesses(value: String) {
        _uiState.update { it.copy(numberOfSuccesses = value) }
    }

    fun togglePercentage() {
        _uiState.update { it.copy(isPercentage = !it.isPercentage) }
        updateProbabilityValues()
    }

    fun toggleFullPrecision() {
        _uiState.update { it.copy(isFullPrecision = !it.isFullPrecision) }
        updateProbabilityValues()
    }

    fun calculateProbabilities() {
        val probability = _uiState.value.probabilityText.toDoubleOrNull() ?: return
        val trials = _uiState.value.numberOfTrials.toIntOrNull() ?: return
        val successes = _uiState.value.numberOfSuccesses.toIntOrNull() ?: return

        if (probability > 1 || trials < successes) return

        val binomialDistribution = BinomialDistribution(trials, probability, successes)
        _uiState.update { currentState ->
            currentState.copy(
                binomialDistribution = binomialDistribution,
                pX = binomialDistribution.probabilityEquals,
                pXLessOrEqual = binomialDistribution.probabilityLessThanOrEqual,
                pXGreaterOrEqual = binomialDistribution.probabilityGreaterThanOrEqual,
                pXLess = binomialDistribution.probabilityLessThan,
                pXGreater = binomialDistribution.probabilityGreaterThan
            )
        }

        _uiState.value.x = _uiState.value.numberOfSuccesses
        updateProbabilityValues()
    }

    private fun updateProbabilityValues() {
        _uiState.update { state ->
            state.copy(
                pXText = formatProbability(state.pX),
                pXLessOrEqualText = formatProbability(state.pXLessOrEqual),
                pXGreaterOrEqualText = formatProbability(state.pXGreaterOrEqual),
                pXLessText = formatProbability(state.pXLess),
                pXGreaterText = formatProbability(state.pXGreater)
            )
        }
    }

    private fun formatProbability(value: Double): String {
        return if (_uiState.value.isPercentage) {
            if (_uiState.value.isFullPrecision) "%012.9f%%".format(value * 100)
            else "%05.2f%%".format(value * 100)
        } else {
            if (_uiState.value.isFullPrecision) "%.9f".format(value)
            else "%.4f".format(value)
        }
    }
}