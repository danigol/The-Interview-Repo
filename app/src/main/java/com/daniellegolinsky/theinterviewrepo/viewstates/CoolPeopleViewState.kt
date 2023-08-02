package com.daniellegolinsky.theinterviewrepo.viewstates

import com.daniellegolinsky.theinterviewrepo.api.models.CoolPersonResponse

data class CoolPeopleViewState(
    val coolPeople: List<CoolPersonResponse>,
    val searchTerm: String,
)
