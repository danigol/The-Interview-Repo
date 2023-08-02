package com.daniellegolinsky.theinterviewrepo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.daniellegolinsky.theinterviewrepo.api.models.CoolPersonResponse

@Composable
fun PersonItem(
    coolPersonResponse: CoolPersonResponse,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "${coolPersonResponse.firstName} ${coolPersonResponse.lastName}",
            fontSize = 20.sp,

        )
        Text(
            text = coolPersonResponse.favoriteColor,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun PreviewPersonItem() {
    PersonItem(
        CoolPersonResponse(
            id = "123",
            firstName = "Alice",
            lastName = "Bob",
            favoriteColor = "Cerulean"
        )
    )
}