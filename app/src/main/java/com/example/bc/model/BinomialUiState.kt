package com.example.bc.model

data class BinomialUiState(
    val probabilityText: String = "",
    val numberOfTrials: String = "",
    val numberOfSuccesses: String = "",
    val isPercentage: Boolean = true,
    val isFullPrecision: Boolean = false,
    val binomialDistribution: BinomialDistribution? = null,
    var x: String = "",
    val pX: Double = 0.0,
    val pXLessOrEqual: Double = 0.0,
    val pXGreaterOrEqual: Double = 0.0,
    val pXLess: Double = 0.0,
    val pXGreater: Double = 0.0,
    val pXText: String = "00.00%",
    val pXLessOrEqualText: String = "00.00%",
    val pXGreaterOrEqualText: String = "00.00%",
    val pXLessText: String = "00.00%",
    val pXGreaterText: String = "00.00%"
)