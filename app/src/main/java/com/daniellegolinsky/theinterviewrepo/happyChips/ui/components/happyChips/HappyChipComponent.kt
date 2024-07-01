package com.daniellegolinsky.theinterviewrepo.happyChips.ui.components.happyChips

import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.daniellegolinsky.theinterviewrepo.happyChips.data.models.HappyChip

@Composable
fun HappyChipComponent(
    chip: HappyChip,
    onChipClick: () -> Unit,
) {
    FilterChip(
        selected = chip.isSelected,
        label = { Text(chip.chipName) },
        onClick = { onChipClick() },
    )
}
