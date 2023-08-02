package com.daniellegolinsky.theinterviewrepo.ui.coolfolks

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniellegolinsky.theinterviewrepo.data.CoolPeopleRepo
import com.daniellegolinsky.theinterviewrepo.viewstates.CoolPeopleViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CoolPeopleViewModel @Inject constructor(
    private val coolPeopleRepo: CoolPeopleRepo
) : ViewModel(){

    private val emptyState: CoolPeopleViewState = CoolPeopleViewState(
        coolPeople = emptyList(), ""
    )
    private var _coolPeopleViewState: MutableStateFlow<CoolPeopleViewState> =
        MutableStateFlow(emptyState)
    val coolPeopleViewState: StateFlow<CoolPeopleViewState> = _coolPeopleViewState

    fun updateSearchTerm(search: String) {
        _coolPeopleViewState.value = CoolPeopleViewState(
            coolPeople = _coolPeopleViewState.value.coolPeople,
            searchTerm = search
        )
    }

    fun fetchCoolPeople() {
        viewModelScope.launch {
            _coolPeopleViewState.value = CoolPeopleViewState(
                coolPeople = coolPeopleRepo.getCoolPeople(),
                searchTerm = _coolPeopleViewState.value.searchTerm
            )
        }
    }

    // Find all the people with the same favorite color
    // Runs search on cached data, if available
    fun filterByFavoriteColor() {
        val searchColor = _coolPeopleViewState.value.searchTerm.lowercase()
        if (searchColor.isNotEmpty()) {
            viewModelScope.launch {
                _coolPeopleViewState.value = CoolPeopleViewState(
                    coolPeople = coolPeopleRepo.getCoolPeople(false).filter { person ->
                        person.favoriteColor.lowercase().contains(searchColor)
                    }, _coolPeopleViewState.value.searchTerm
                )
            }
        } else {
            fetchCoolPeople()
        }
    }
}