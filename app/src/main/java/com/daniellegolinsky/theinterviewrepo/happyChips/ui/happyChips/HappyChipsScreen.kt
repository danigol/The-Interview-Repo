package com.daniellegolinsky.theinterviewrepo.happyChips.ui.happyChips

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniellegolinsky.theinterviewrepo.happyChips.data.models.HappyChip
import com.daniellegolinsky.theinterviewrepo.happyChips.ui.components.happyChips.HappyChipComponent

@Composable
fun HappyChipsScreen(
    chipList: List<HappyChip>, // TODO ViewModel instead
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("What made you happy today?")
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 64.dp)) {
            items(chipList) { chip ->
                HappyChipComponent(chip, {})
            }
        }
    }
}

@Preview
@Composable
fun PreviewHappyChipsScreen() {
    HappyChipsScreen(
        chipList = listOf(
            HappyChip("Chips", false),
            HappyChip("Snacks", true),
            HappyChip("A really big burrito", false) // Sad, I know :(
        ),
    )
}