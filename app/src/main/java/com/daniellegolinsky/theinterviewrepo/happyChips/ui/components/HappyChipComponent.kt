package com.daniellegolinsky.theinterviewrepo.happyChips.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daniellegolinsky.theinterviewrepo.happyChips.data.models.HappyChip

@Composable
fun HappyChipComponent(
    chip: HappyChip,
    onChipClick: () -> Unit,
) {
    FilterChip(
        selected = chip.isSelected,
        label = {
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = chip.chipName,
                    maxLines = 2,
                )
            }
        },
        onClick = { onChipClick() },
        modifier = Modifier.padding(start = 3.dp, end = 3.dp)
    )
}
