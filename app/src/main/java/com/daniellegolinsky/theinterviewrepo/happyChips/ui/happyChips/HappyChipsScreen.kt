package com.daniellegolinsky.theinterviewrepo.happyChips.ui.happyChips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daniellegolinsky.theinterviewrepo.R
import com.daniellegolinsky.theinterviewrepo.happyChips.data.models.HappyChip
import com.daniellegolinsky.theinterviewrepo.happyChips.ui.components.happyChips.HappyChipComponent

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HappyChipsScreen(
    viewModel: HappyChipsViewModel,
    modifier: Modifier = Modifier,
) {
    val viewState = viewModel.chipViewStateFlow.collectAsStateWithLifecycle().value

    Column(modifier = modifier) {
        Text(
            text = "What made you happy today?",
            fontSize = 28.sp, // TODO All of these replace with constants and custom composables
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        // The newer way of doing this is MUCH cleaner for dynamic chips
//        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 96.dp)) {
//            items(chipList) { chip ->
//                HappyChipComponent(chip, {})
//            }
//        }
        // Not only is it cleaner for dynamic content,
        // a FlowRow allows us to add static chips, like the "Other" option.
        // See: https://developer.android.com/develop/ui/compose/layouts/flow
        FlowRow(
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            viewState.chips.forEach { chip ->
                HappyChipComponent(
                    chip = HappyChip(chipName = chip.key, isSelected = chip.value),
                    onChipClick = { viewModel.selectChip(chip.key) }
                )
            }
            // The "Other" Chip, which we'll always have
            // Also TODO: make it a separate composable
            HappyChipComponent(
                chip = HappyChip(stringResource(id = R.string.other_chip), false),
                onChipClick = {
                    // TODO Okay, maybe not, but it's a placeholder!
                    viewModel.addChip("MORE CHIPS!")
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewHappyChipsScreen() {
    HappyChipsScreen(viewModel())
}