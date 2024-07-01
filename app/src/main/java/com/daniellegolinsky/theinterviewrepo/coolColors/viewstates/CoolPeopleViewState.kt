package com.daniellegolinsky.theinterviewrepo.coolColors.viewstates

import com.daniellegolinsky.theinterviewrepo.coolColors.models.CoolPersonResponse

data class CoolPeopleViewState(
    val coolPeople: List<CoolPersonResponse>,
    val searchTerm: String,
)
