package com.daniellegolinsky.theinterviewrepo.happyChips.ui.happyChips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.daniellegolinsky.theinterviewrepo.happyChips.ui.components.AddChipDialog
import com.daniellegolinsky.theinterviewrepo.happyChips.ui.components.HappyChipComponent

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HappyChipsScreen(
    viewModel: HappyChipsViewModel,
    modifier: Modifier = Modifier,
) {
    val viewState = viewModel.chipViewStateFlow.collectAsStateWithLifecycle().value
    val addTagDialogVisible = remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        if (addTagDialogVisible.value) {
            AddChipDialog(
                onAdd = { newTag: String -> viewModel.addChip(newTag) },
                onCancel = { addTagDialogVisible.value = false },
            )
        }
        Text(
            text = stringResource(id = R.string.tag_prompt),
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
                    addTagDialogVisible.value = true
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