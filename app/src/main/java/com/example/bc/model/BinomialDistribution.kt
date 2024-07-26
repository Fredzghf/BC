package com.example.bc.model

import kotlin.math.exp
import kotlin.math.ln

class BinomialDistribution(
    private val n: Int,
    private val p: Double,
    private val x: Int
) {
    val probabilityEquals: Double by lazy { calculateProbabilityEquals() }
    val probabilityLessThanOrEqual: Double by lazy { calculateProbabilityLessThanOrEqual() }
    val probabilityGreaterThanOrEqual: Double by lazy { calculateProbabilityGreaterThanOrEqual() }
    val probabilityLessThan: Double by lazy { 1 - probabilityGreaterThanOrEqual }
    val probabilityGreaterThan: Double by lazy { 1 - probabilityLessThanOrEqual }

    private val logFactorials: DoubleArray by lazy { calculateLogFactorials(n) }

    private fun calculateLogFactorials(n: Int): DoubleArray {
        val result = DoubleArray(n + 1)
        result[0] = 0.0
        for (i in 1..n) {
            result[i] = result[i - 1] + ln(i.toDouble())
        }
        return result
    }

    private fun calculateProbabilityEquals(): Double {
        return exp(logBinomialProbability(x))
    }

    private fun calculateProbabilityLessThanOrEqual(): Double {
        return (0..x).sumOf { k ->
            exp(logBinomialProbability(k))
        }
    }

    private fun calculateProbabilityGreaterThanOrEqual(): Double {
        return (x..n).sumOf { k ->
            exp(logBinomialProbability(k))
        }
    }

    private fun logBinomialProbability(k: Int): Double {
        return logFactorials[n] - logFactorials[k] - logFactorials[n - k] +
                k * ln(p) + (n - k) * ln(1 - p)
    }
}