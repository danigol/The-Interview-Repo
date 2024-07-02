package com.daniellegolinsky.theinterviewrepo.happyChips.ui.happyChips

import androidx.lifecycle.ViewModel
import com.daniellegolinsky.theinterviewrepo.happyChips.data.repos.ChipRepo
import com.daniellegolinsky.theinterviewrepo.happyChips.data.viewStates.HappyChipScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.internal.toImmutableMap
import javax.inject.Inject

@HiltViewModel
class HappyChipsViewModel @Inject constructor(chipRepo: ChipRepo) : ViewModel() {

    private var _chipViewStateFlow: MutableStateFlow<HappyChipScreenViewState> =
        MutableStateFlow(HappyChipScreenViewState(chips = chipRepo.getDefaultChips()))
    val chipViewStateFlow: StateFlow<HappyChipScreenViewState> = _chipViewStateFlow

    fun selectChip(selectedChip: String) {
        if (_chipViewStateFlow.value.chips.containsKey(selectedChip)) {
            val currentValue = _chipViewStateFlow.value.chips[selectedChip] ?: false
            val newChipList = _chipViewStateFlow.value.chips.toMutableMap()
            newChipList[selectedChip] = !currentValue
            _chipViewStateFlow.value =
                generateNewStateForChipListUpdate(newChipList.toImmutableMap(), null)
        }
    }

    fun addChip(tag: String) {
        val newTag = tag.trim()
        if (newTag.isNotEmpty()) {
            // Update the flow and add the new tag as a selected chip
            _chipViewStateFlow.value = generateNewStateForChipListUpdate(
                _chipViewStateFlow.value.chips, newTag
            )
        }
    }

    private fun generateNewStateForChipListUpdate(
        chipList: Map<String, Boolean>,
        newTag: String?
    ): HappyChipScreenViewState {
        return if (newTag != null) {
            val newTags = chipList.toMutableMap()
            newTags[newTag] = true
            HappyChipScreenViewState(newTags.toImmutableMap())
        } else {
            HappyChipScreenViewState(chipList)
        }
    }
}
