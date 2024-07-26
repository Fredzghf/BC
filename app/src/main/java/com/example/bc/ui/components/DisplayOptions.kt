package com.example.bc.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bc.ui.icons.DecimalIncreaseIcon
import com.example.bc.ui.icons.PercentIcon

@Composable
fun DisplayOptions(
    isPercentage: Boolean,
    isFullPrecision: Boolean,
    onPercentageChange: () -> Unit,
    onFullPrecisionChange: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Switch(
            checked = isPercentage,
            onCheckedChange = { onPercentageChange() },
            thumbContent = {
                Icon(
                    imageVector = PercentIcon,
                    contentDescription = "Percentage",
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        )
        Switch(
            checked = isFullPrecision,
            onCheckedChange = { onFullPrecisionChange() },
            thumbContent = {
                Icon(
                    imageVector = DecimalIncreaseIcon,
                    contentDescription = "Full Precision",
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        )
    }
}