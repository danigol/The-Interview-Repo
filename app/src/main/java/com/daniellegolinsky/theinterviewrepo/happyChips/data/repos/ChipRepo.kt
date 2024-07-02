package com.daniellegolinsky.theinterviewrepo.happyChips.data.repos

import javax.inject.Inject

class ChipRepo @Inject constructor() {
    // TODO Normally we'd inject a resource provider into the repo,
    //  but that's more of a 1-day project than a 2 hour project
    private val defaultChips: Map<String, Boolean> = mapOf(
        "Family" to false,
        "Friends" to false,
        "Work" to false,
    )

    fun getDefaultChips(): Map<String, Boolean> = defaultChips
}