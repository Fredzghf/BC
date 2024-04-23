package com.example.bc

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bc.MainActivity.Companion.fullPrecision
import com.example.bc.MainActivity.Companion.pX
import com.example.bc.MainActivity.Companion.pXGreater
import com.example.bc.MainActivity.Companion.pXGreaterOrEqual
import com.example.bc.MainActivity.Companion.pXLess
import com.example.bc.MainActivity.Companion.pXLessOrEqual
import com.example.bc.MainActivity.Companion.percentage
import com.example.bc.MainActivity.Companion.textpX
import com.example.bc.MainActivity.Companion.textpXGreater
import com.example.bc.MainActivity.Companion.textpXGreaterOrEqual
import com.example.bc.MainActivity.Companion.textpXLess
import com.example.bc.MainActivity.Companion.textpXLessOrEqual
import com.example.bc.ui.theme.BCTheme
import java.math.BigInteger
import java.util.Locale
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BCTheme {
                MainPageLayout()
            }
        }
    }

    companion object {
        var textpX = mutableStateOf("P(X = x)")
        var pX = mutableStateOf("0.0")
        var textpXLessOrEqual = mutableStateOf("P(X ≤ x)")
        var pXLessOrEqual = mutableStateOf("0.0")
        var textpXGreaterOrEqual = mutableStateOf("P(X ≥ x)")
        var pXGreaterOrEqual = mutableStateOf("0.0")
        var textpXLess = mutableStateOf("P(X < x)")
        var pXLess = mutableStateOf("0.0")
        var textpXGreater = mutableStateOf("P(X > x)")
        var pXGreater = mutableStateOf("0.0")
        var percentage = mutableStateOf(true)
        var fullPrecision = mutableStateOf(false)
    }
}

@Preview(showBackground = true)
@Composable
fun MainPageLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val probabilityText = remember { TextFieldState() }
        val numberOfTrials = remember { TextFieldState() }
        val numberOfSuccesses = remember { TextFieldState() }

        Row {
            Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                TextFieldComposable(text = "Probability", value = probabilityText, isInt = false)
            }
            Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                TextFieldComposable(text = "Successes", value = numberOfSuccesses, isInt = true)
            }
            Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                TextFieldComposable(text = "Trials", value = numberOfTrials, isInt = true)
            }
        }


        val context = LocalContext.current
        Button(
            onClick = {
                if (probabilityText.text.isEmpty() || numberOfTrials.text.isEmpty() || numberOfSuccesses.text.isEmpty()) {
                    Toast.makeText(
                        context,
                        "All fields must be filled",
                        Toast.LENGTH_LONG
                    ).show()
                    return@Button
                }
                calculateProbabilities(
                    probabilityText.text.toDouble(),
                    numberOfTrials.text.toInt(),
                    numberOfSuccesses.text.toInt(),
                    context
                )
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 32.dp)
        ) {
            Text(
                text = "Calculate",
                fontSize = 20.sp)
        }

        textpX = remember { mutableStateOf("P(X = x)") }
        pX = remember { mutableStateOf("0.00%") }
        LabelComposable(text = textpX, value = pX)

        textpXLessOrEqual = remember { mutableStateOf("P(X ≤ x)") }
        pXLessOrEqual = remember { mutableStateOf("0.00%") }
        LabelComposable(text = textpXLessOrEqual, value = pXLessOrEqual)

        textpXGreaterOrEqual = remember { mutableStateOf("P(X ≥ x)") }
        pXGreaterOrEqual = remember { mutableStateOf("0.00%") }
        LabelComposable(text = textpXGreaterOrEqual, value = pXGreaterOrEqual)

        textpXLess = remember { mutableStateOf("P(X < x)") }
        pXLess = remember { mutableStateOf("0.00%") }
        LabelComposable(text = textpXLess, value = pXLess)

        textpXGreater = remember { mutableStateOf("P(X > x)") }
        pXGreater = remember { mutableStateOf("0.00%") }
        LabelComposable(text = textpXGreater, value = pXGreater)

        var percentageDisplay by remember { percentage }
        var fullPrecisionDisplay by remember { fullPrecision }
        Row (horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Switch(
                checked = percentageDisplay,
                onCheckedChange = {
                    percentageDisplay = it
                    // Update the values
                    pX.value = if (it) probabilityToPercentage(pX.value) else percentageToProbability(pX.value)
                    pXLessOrEqual.value = if (it) probabilityToPercentage(pXLessOrEqual.value) else percentageToProbability(pXLessOrEqual.value)
                    pXGreaterOrEqual.value = if (it) probabilityToPercentage(pXGreaterOrEqual.value) else percentageToProbability(pXGreaterOrEqual.value)
                    pXLess.value = if (it) probabilityToPercentage(pXLess.value) else percentageToProbability(pXLess.value)
                    pXGreater.value = if (it) probabilityToPercentage(pXGreater.value) else percentageToProbability(pXGreater.value)
                },
                thumbContent = {
                    Icon(imageVector = rememberPercent()
                        , contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize))
                },
                modifier = Modifier.padding(bottom = 82.dp)
            )

            Switch(
                checked = fullPrecisionDisplay,
                onCheckedChange = {
                    fullPrecisionDisplay = it
                    // Update the values
                    pX.value = if (percentageDisplay) percentageToPercentage(pX.value) else probabilityToProbability(pX.value)
                    pXLessOrEqual.value = if (percentageDisplay) percentageToPercentage(pXLessOrEqual.value) else probabilityToProbability(pXLessOrEqual.value)
                    pXGreaterOrEqual.value = if (percentageDisplay) percentageToPercentage(pXGreaterOrEqual.value) else probabilityToProbability(pXGreaterOrEqual.value)
                    pXLess.value = if (percentageDisplay) percentageToPercentage(pXLess.value) else probabilityToProbability(pXLess.value)
                    pXGreater.value = if (percentageDisplay) percentageToPercentage(pXGreater.value) else probabilityToProbability(pXGreater.value)
                },
                thumbContent = {
                    Icon(imageVector = rememberDecimalIncrease()
                        , contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize))
                },
                modifier = Modifier.padding(bottom = 82.dp)
            )
        }
    }
}

@Composable
fun TextFieldComposable(
    text: String,
    value: TextFieldState = TextFieldState(),
    isInt: Boolean = true,
) {
    Text(
        text = text,
        modifier = Modifier.padding(bottom = 8.dp),
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 20.sp
    )

    TextField(
        value = value.text,
        onValueChange = {
            if (it.isEmpty()){
                value.text = it
            } else {
                if (isInt) {
                    value.text = when (it.toIntOrNull()) {
                        null -> value.text
                        else -> it.trim()
                    }
                } else {
                    value.text = when (it.toDoubleOrNull()) {
                        null -> value.text
                        else -> it.trim()
                    }
                }

            }
        },
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isInt) KeyboardType.Number else KeyboardType.Decimal
        ),
        textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp)
    )

    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
fun LabelComposable(
    text: MutableState<String>,
    value: MutableState<String>
) {
    val probabilityValue by value
    val textValue by text

    Row(
        modifier = Modifier.fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = textValue,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )

        Text(
            text = probabilityValue,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )
    }
}

fun calculateProbabilities(
    probability: Double,
    numberOfTrials: Int,
    numberOfSuccesses: Int,
    context: Context
) {
    if (probability > 1) {
        Toast.makeText(
            context,
            "Probability must be between 0 and 1",
            Toast.LENGTH_LONG
        ).show()
        return
    }

    if (numberOfTrials < numberOfSuccesses) {
        Toast.makeText(
            context,
            "Successes cannot be greater than Trials",
            Toast.LENGTH_LONG
        ).show()
        return
    }

    val binomialDistributionProbabilities = calculateBinomialDistributionProbabilities(
        numberOfTrials,
        probability,
        numberOfSuccesses
    )

    textpX.value = "P(X = $numberOfSuccesses)"
    textpXLessOrEqual.value = "P(X ≤ $numberOfSuccesses)"
    textpXGreaterOrEqual.value = "P(X ≥ $numberOfSuccesses)"
    textpXLess.value = "P(X < $numberOfSuccesses)"
    textpXGreater.value = "P(X > $numberOfSuccesses)"

    if (percentage.value) {
        pX.value = toPercentage(binomialDistributionProbabilities.pX)
        pXLessOrEqual.value = toPercentage(binomialDistributionProbabilities.pXLessOrEqual)
        pXGreaterOrEqual.value = toPercentage(binomialDistributionProbabilities.pXGreaterOrEqual)
        pXLess.value = toPercentage(binomialDistributionProbabilities.pXLess)
        pXGreater.value = toPercentage(binomialDistributionProbabilities.pXGreater)
    }else{
        pX.value = toProbability(binomialDistributionProbabilities.pX)
        pXLessOrEqual.value = toProbability(binomialDistributionProbabilities.pXLessOrEqual)
        pXGreaterOrEqual.value = toProbability(binomialDistributionProbabilities.pXGreaterOrEqual)
        pXLess.value = toProbability(binomialDistributionProbabilities.pXLess)
        pXGreater.value = toProbability(binomialDistributionProbabilities.pXGreater)
    }
}

fun binomialCoefficient(n: Int, k: Int): BigInteger {
    if (k == 0 || k == n) {
        return BigInteger.ONE
    }
    val num = (n - k + 1..n).fold(BigInteger.ONE) { acc, i -> acc * BigInteger.valueOf(i.toLong()) }
    val den = (2..k).fold(BigInteger.ONE) { acc, i -> acc * BigInteger.valueOf(i.toLong()) }
    return num / den
}

fun binomialProbability(n: Int, p: Double, x: Int): Double {
    val coeff = binomialCoefficient(n, x).toDouble()
    return coeff * p.pow(x.toDouble()) * (1.0 - p).pow((n - x).toDouble())
}

fun calculateBinomialDistributionProbabilities(n: Int, p: Double, x: Int): BinomialDistributionProbabilities {
    val pX = binomialProbability(n, p, x)
    val pXLessOrEqual = (0..x).sumOf { binomialProbability(n, p, it) }
    val pXGreaterOrEqual = (x..n).sumOf { binomialProbability(n, p, it) }
    val pXLess = (0 until x).sumOf { binomialProbability(n, p, it) }
    val pXGreater = (x + 1..n).sumOf { binomialProbability(n, p, it) }

    return BinomialDistributionProbabilities(pX, pXLessOrEqual, pXGreaterOrEqual, pXLess, pXGreater)
}

fun probabilityToPercentage(probability: String): String {
    return toPercentage(probability.toDouble() * 100)
}

fun percentageToProbability(percentage: String): String {
    return toProbability(percentage.dropLast(1).toDouble() / 100)
}

fun probabilityToProbability(probability: String): String {
    return toProbability(probability.toDouble())
}

fun percentageToPercentage(percentage: String): String {
    return toPercentage(percentage.dropLast(1).toDouble())
}

fun toPercentage(probability: Double): String {
    return if (fullPrecision.value)
        "%f%%".format(locale = Locale.ENGLISH, probability)
    else
        "%.2f%%".format(locale = Locale.ENGLISH, probability)
}

fun toProbability(probability: Double): String {
    return if (fullPrecision.value)
        "%f".format(locale = Locale.ENGLISH, probability)
    else
        "%.4f".format(locale = Locale.ENGLISH, probability)
}

class TextFieldState {
    var text: String by mutableStateOf("")
}

data class BinomialDistributionProbabilities(
    val pX: Double,
    val pXLessOrEqual: Double,
    val pXGreaterOrEqual: Double,
    val pXLess: Double,
    val pXGreater: Double
)

@Composable
fun rememberPercent(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "percent",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12.208f, 17.708f)
                quadToRelative(-2.25f, 0f, -3.854f, -1.625f)
                reflectiveQuadTo(6.75f, 12.208f)
                quadToRelative(0f, -2.25f, 1.604f, -3.875f)
                reflectiveQuadToRelative(3.854f, -1.625f)
                quadToRelative(2.292f, 0f, 3.896f, 1.625f)
                reflectiveQuadToRelative(1.604f, 3.875f)
                quadToRelative(0f, 2.292f, -1.604f, 3.896f)
                reflectiveQuadToRelative(-3.896f, 1.604f)
                close()
                moveToRelative(0f, -2.666f)
                quadToRelative(1.209f, 0f, 2.042f, -0.813f)
                quadToRelative(0.833f, -0.812f, 0.833f, -2.021f)
                quadToRelative(0f, -1.166f, -0.833f, -2f)
                quadToRelative(-0.833f, -0.833f, -2.042f, -0.833f)
                quadToRelative(-1.166f, 0f, -2f, 0.813f)
                quadToRelative(-0.833f, 0.812f, -0.833f, 2.02f)
                quadToRelative(0f, 1.209f, 0.833f, 2.021f)
                quadToRelative(0.834f, 0.813f, 2f, 0.813f)
                close()
                moveTo(27.792f, 33.25f)
                quadToRelative(-2.292f, 0f, -3.896f, -1.604f)
                reflectiveQuadToRelative(-1.604f, -3.854f)
                quadToRelative(0f, -2.292f, 1.625f, -3.896f)
                reflectiveQuadToRelative(3.875f, -1.604f)
                quadToRelative(2.25f, 0f, 3.875f, 1.604f)
                reflectiveQuadToRelative(1.625f, 3.896f)
                quadToRelative(0f, 2.25f, -1.625f, 3.854f)
                reflectiveQuadToRelative(-3.875f, 1.604f)
                close()
                moveToRelative(0f, -2.625f)
                quadToRelative(1.166f, 0f, 2f, -0.833f)
                quadToRelative(0.833f, -0.834f, 0.833f, -2f)
                quadToRelative(0f, -1.209f, -0.813f, -2.042f)
                quadToRelative(-0.812f, -0.833f, -2.02f, -0.833f)
                quadToRelative(-1.209f, 0f, -2.021f, 0.833f)
                quadToRelative(-0.813f, 0.833f, -0.813f, 2.042f)
                quadToRelative(0f, 1.166f, 0.813f, 2f)
                quadToRelative(0.812f, 0.833f, 2.021f, 0.833f)
                close()
                moveTo(7.708f, 32.292f)
                quadToRelative(-0.416f, -0.375f, -0.416f, -0.896f)
                reflectiveQuadToRelative(0.416f, -0.938f)
                lineToRelative(22.75f, -22.791f)
                quadToRelative(0.417f, -0.375f, 0.938f, -0.375f)
                quadToRelative(0.521f, 0f, 0.896f, 0.416f)
                quadToRelative(0.416f, 0.375f, 0.416f, 0.896f)
                reflectiveQuadToRelative(-0.416f, 0.938f)
                lineTo(9.542f, 32.333f)
                quadToRelative(-0.417f, 0.375f, -0.938f, 0.375f)
                quadToRelative(-0.521f, 0f, -0.896f, -0.416f)
                close()
            }
        }.build()
    }
}
@Composable
fun rememberDecimalIncrease(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "decimal_increase",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(31.5f, 31.292f)
                horizontalLineTo(21.375f)
                quadToRelative(-0.542f, 0f, -0.917f, -0.375f)
                reflectiveQuadTo(20.083f, 30f)
                quadToRelative(0f, -0.542f, 0.375f, -0.938f)
                quadToRelative(0.375f, -0.395f, 0.917f, -0.395f)
                horizontalLineTo(31.5f)
                lineToRelative(-2.042f, -2.084f)
                quadToRelative(-0.375f, -0.375f, -0.396f, -0.895f)
                quadToRelative(-0.02f, -0.521f, 0.396f, -0.938f)
                quadToRelative(0.417f, -0.375f, 0.938f, -0.375f)
                quadToRelative(0.521f, 0f, 0.937f, 0.375f)
                lineToRelative(4.292f, 4.333f)
                quadToRelative(0.417f, 0.375f, 0.417f, 0.917f)
                reflectiveQuadToRelative(-0.417f, 0.917f)
                lineToRelative(-4.292f, 4.333f)
                quadToRelative(-0.416f, 0.375f, -0.937f, 0.375f)
                quadToRelative(-0.521f, 0f, -0.938f, -0.375f)
                quadToRelative(-0.416f, -0.417f, -0.396f, -0.938f)
                quadToRelative(0.021f, -0.52f, 0.396f, -0.937f)
                close()
                moveTo(6.375f, 21.583f)
                horizontalLineTo(4.708f)
                quadToRelative(-0.541f, 0f, -0.916f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.916f)
                verticalLineToRelative(-1.667f)
                quadToRelative(0f, -0.583f, 0.375f, -0.958f)
                reflectiveQuadToRelative(0.916f, -0.375f)
                horizontalLineToRelative(1.667f)
                quadToRelative(0.292f, 0f, 0.521f, 0.104f)
                quadToRelative(0.229f, 0.104f, 0.416f, 0.292f)
                quadToRelative(0.188f, 0.187f, 0.292f, 0.416f)
                quadToRelative(0.104f, 0.229f, 0.104f, 0.521f)
                verticalLineToRelative(1.667f)
                quadToRelative(0f, 0.25f, -0.104f, 0.5f)
                reflectiveQuadToRelative(-0.292f, 0.416f)
                quadToRelative(-0.187f, 0.167f, -0.416f, 0.271f)
                quadToRelative(-0.229f, 0.104f, -0.521f, 0.104f)
                close()
                moveToRelative(9.458f, 0f)
                quadToRelative(-2.375f, 0f, -4.062f, -1.687f)
                quadToRelative(-1.688f, -1.688f, -1.688f, -4.063f)
                verticalLineTo(9.167f)
                quadToRelative(0f, -2.375f, 1.688f, -4.084f)
                quadToRelative(1.687f, -1.708f, 4.062f, -1.708f)
                reflectiveQuadToRelative(4.084f, 1.708f)
                quadToRelative(1.708f, 1.709f, 1.708f, 4.084f)
                verticalLineToRelative(6.666f)
                quadToRelative(0f, 2.375f, -1.708f, 4.063f)
                quadToRelative(-1.709f, 1.687f, -4.084f, 1.687f)
                close()
                moveToRelative(15f, 0f)
                quadToRelative(-2.375f, 0f, -4.062f, -1.687f)
                quadToRelative(-1.688f, -1.688f, -1.688f, -4.063f)
                verticalLineTo(9.167f)
                quadToRelative(0f, -2.375f, 1.688f, -4.084f)
                quadToRelative(1.687f, -1.708f, 4.062f, -1.708f)
                reflectiveQuadToRelative(4.084f, 1.708f)
                quadToRelative(1.708f, 1.709f, 1.708f, 4.084f)
                verticalLineToRelative(6.666f)
                quadToRelative(0f, 2.375f, -1.708f, 4.063f)
                quadToRelative(-1.709f, 1.687f, -4.084f, 1.687f)
                close()
                moveToRelative(-15f, -2.625f)
                quadToRelative(1.292f, 0f, 2.209f, -0.916f)
                quadToRelative(0.916f, -0.917f, 0.916f, -2.209f)
                verticalLineTo(9.167f)
                quadToRelative(0f, -1.292f, -0.916f, -2.209f)
                quadToRelative(-0.917f, -0.916f, -2.209f, -0.916f)
                quadToRelative(-1.291f, 0f, -2.208f, 0.916f)
                quadToRelative(-0.917f, 0.917f, -0.917f, 2.209f)
                verticalLineToRelative(6.666f)
                quadToRelative(0f, 1.292f, 0.917f, 2.209f)
                quadToRelative(0.917f, 0.916f, 2.208f, 0.916f)
                close()
                moveToRelative(15f, 0f)
                quadToRelative(1.292f, 0f, 2.209f, -0.916f)
                quadToRelative(0.916f, -0.917f, 0.916f, -2.209f)
                verticalLineTo(9.167f)
                quadToRelative(0f, -1.292f, -0.916f, -2.209f)
                quadToRelative(-0.917f, -0.916f, -2.209f, -0.916f)
                quadToRelative(-1.291f, 0f, -2.208f, 0.916f)
                quadToRelative(-0.917f, 0.917f, -0.917f, 2.209f)
                verticalLineToRelative(6.666f)
                quadToRelative(0f, 1.292f, 0.917f, 2.209f)
                quadToRelative(0.917f, 0.916f, 2.208f, 0.916f)
                close()
            }
        }.build()
    }
}

@Composable
fun rememberDecimalDecrease(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "decimal_decrease",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(24.708f, 31.292f)
                lineToRelative(2.084f, 2.083f)
                quadToRelative(0.375f, 0.417f, 0.375f, 0.937f)
                quadToRelative(0f, 0.521f, -0.375f, 0.938f)
                quadToRelative(-0.417f, 0.375f, -0.959f, 0.375f)
                quadToRelative(-0.541f, 0f, -0.958f, -0.375f)
                lineToRelative(-4.292f, -4.333f)
                quadToRelative(-0.375f, -0.375f, -0.375f, -0.917f)
                reflectiveQuadToRelative(0.375f, -0.917f)
                lineToRelative(4.292f, -4.333f)
                quadToRelative(0.417f, -0.375f, 0.958f, -0.375f)
                quadToRelative(0.542f, 0f, 0.959f, 0.375f)
                quadToRelative(0.375f, 0.417f, 0.375f, 0.938f)
                quadToRelative(0f, 0.52f, -0.375f, 0.895f)
                lineToRelative(-2.084f, 2.084f)
                horizontalLineToRelative(10.125f)
                quadToRelative(0.542f, 0f, 0.938f, 0.395f)
                quadToRelative(0.396f, 0.396f, 0.396f, 0.938f)
                quadToRelative(0f, 0.542f, -0.396f, 0.917f)
                reflectiveQuadToRelative(-0.938f, 0.375f)
                close()
                moveTo(6.375f, 21.583f)
                horizontalLineTo(4.708f)
                quadToRelative(-0.541f, 0f, -0.916f, -0.375f)
                reflectiveQuadToRelative(-0.375f, -0.916f)
                verticalLineToRelative(-1.667f)
                quadToRelative(0f, -0.583f, 0.375f, -0.958f)
                reflectiveQuadToRelative(0.916f, -0.375f)
                horizontalLineToRelative(1.667f)
                quadToRelative(0.292f, 0f, 0.521f, 0.104f)
                quadToRelative(0.229f, 0.104f, 0.416f, 0.292f)
                quadToRelative(0.188f, 0.187f, 0.292f, 0.416f)
                quadToRelative(0.104f, 0.229f, 0.104f, 0.521f)
                verticalLineToRelative(1.667f)
                quadToRelative(0f, 0.25f, -0.104f, 0.5f)
                reflectiveQuadToRelative(-0.292f, 0.416f)
                quadToRelative(-0.187f, 0.167f, -0.416f, 0.271f)
                quadToRelative(-0.229f, 0.104f, -0.521f, 0.104f)
                close()
                moveToRelative(9.458f, 0f)
                quadToRelative(-2.375f, 0f, -4.062f, -1.687f)
                quadToRelative(-1.688f, -1.688f, -1.688f, -4.063f)
                verticalLineTo(9.167f)
                quadToRelative(0f, -2.375f, 1.688f, -4.084f)
                quadToRelative(1.687f, -1.708f, 4.062f, -1.708f)
                reflectiveQuadToRelative(4.084f, 1.708f)
                quadToRelative(1.708f, 1.709f, 1.708f, 4.084f)
                verticalLineToRelative(6.666f)
                quadToRelative(0f, 2.375f, -1.708f, 4.063f)
                quadToRelative(-1.709f, 1.687f, -4.084f, 1.687f)
                close()
                moveToRelative(0f, -2.625f)
                quadToRelative(1.292f, 0f, 2.209f, -0.916f)
                quadToRelative(0.916f, -0.917f, 0.916f, -2.209f)
                verticalLineTo(9.167f)
                quadToRelative(0f, -1.292f, -0.916f, -2.209f)
                quadToRelative(-0.917f, -0.916f, -2.209f, -0.916f)
                quadToRelative(-1.291f, 0f, -2.208f, 0.916f)
                quadToRelative(-0.917f, 0.917f, -0.917f, 2.209f)
                verticalLineToRelative(6.666f)
                quadToRelative(0f, 1.292f, 0.917f, 2.209f)
                quadToRelative(0.917f, 0.916f, 2.208f, 0.916f)
                close()
            }
        }.build()
    }
}
