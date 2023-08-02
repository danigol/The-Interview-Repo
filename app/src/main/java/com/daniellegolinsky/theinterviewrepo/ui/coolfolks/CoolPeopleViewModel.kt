package com.daniellegolinsky.theinterviewrepo.ui.coolfolks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniellegolinsky.theinterviewrepo.data.CoolPeopleRepo
import com.daniellegolinsky.theinterviewrepo.viewstates.CoolPeopleViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoolPeopleViewModel @Inject constructor(
    private val coolPeopleRepo: CoolPeopleRepo
) : ViewModel(){

    private val emptyState: CoolPeopleViewState = CoolPeopleViewState(
        coolPeople = emptyList()
    )
    private var _coolPeopleViewState: MutableStateFlow<CoolPeopleViewState> =
        MutableStateFlow(emptyState)
    val coolPeopleViewState: StateFlow<CoolPeopleViewState> = _coolPeopleViewState

    fun fetchCoolPeople() {
        viewModelScope.launch {
            _coolPeopleViewState.value = CoolPeopleViewState(
                coolPeople = coolPeopleRepo.getCoolPeople()
            )
        }
    }
}