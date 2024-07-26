package com.example.bc.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val PercentIcon: ImageVector
    get() {
        return ImageVector.Builder(
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
                // Path data for percent icon
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

val DecimalIncreaseIcon: ImageVector
    get() {
        return ImageVector.Builder(
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
                // Path data for decimal increase icon
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