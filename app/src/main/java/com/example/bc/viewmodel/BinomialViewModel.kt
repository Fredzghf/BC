package com.example.bc.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bc.R
import com.example.bc.model.BinomialDistribution
import com.example.bc.model.BinomialUiState
import com.example.bc.utils.ResourceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BinomialViewModel(private val resourceManager: ResourceManager) : ViewModel() {
    private val _uiState = MutableStateFlow(BinomialUiState())
    val uiState: StateFlow<BinomialUiState> = _uiState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

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
        val probability = _uiState.value.probabilityText.toDoubleOrNull()
        val trials = _uiState.value.numberOfTrials.toIntOrNull()
        val successes = _uiState.value.numberOfSuccesses.toIntOrNull()

        when {
            probability == null || trials == null || successes == null -> {
                _errorMessage.value =
                    resourceManager.getString(R.string.all_fields_must_be_filled_with_valid_numbers)
                return
            }
            probability < 0 || probability > 1 -> {
                _errorMessage.value =
                    resourceManager.getString(R.string.probability_must_be_between_0_and_1)
                return
            }
            trials < successes -> {
                _errorMessage.value =
                    resourceManager.getString(R.string.successes_cannot_be_greater_than_trials)
                return
            }
            trials <= 0 -> {
                _errorMessage.value =
                    resourceManager.getString(R.string.trials_must_be_greater_than_0)
                return
            }
        }

        val binomialDistribution = BinomialDistribution(trials!!, probability!!, successes!!)
        _uiState.update { currentState ->
            currentState.copy(
                binomialDistribution = binomialDistribution,
                pX = binomialDistribution.probabilityEquals,
                pXLessOrEqual = binomialDistribution.probabilityLessThanOrEqual,
                pXGreaterOrEqual = binomialDistribution.probabilityGreaterThanOrEqual,
                pXLess = binomialDistribution.probabilityLessThan,
                pXGreater = binomialDistribution.probabilityGreaterThan,
                x = successes.toString()
            )
        }
        updateProbabilityValues()
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
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
            if (_uiState.value.isFullPrecision) "%.9f%%".format(value * 100)
            else "%.2f%%".format(value * 100)
        } else {
            if (_uiState.value.isFullPrecision) "%.9f".format(value)
            else "%.4f".format(value)
        }
    }
}