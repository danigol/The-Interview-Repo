package com.daniellegolinsky.theinterviewrepo.api.models

import com.google.gson.annotations.SerializedName

data class CoolPersonResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("favorite_color")
    val favoriteColor: String,

    var isLiked: Boolean?,

)
