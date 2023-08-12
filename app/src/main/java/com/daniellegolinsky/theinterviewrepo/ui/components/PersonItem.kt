package com.daniellegolinsky.theinterviewrepo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daniellegolinsky.theinterviewrepo.R
import com.daniellegolinsky.theinterviewrepo.api.models.CoolPersonResponse
import com.daniellegolinsky.theinterviewrepo.ui.coolfolks.CoolPeopleViewModel

@Composable
fun PersonItem(
    coolPersonResponse: CoolPersonResponse,
    viewModel: CoolPeopleViewModel,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth()
    ) {
        Column() {
            Text(
                text = "${coolPersonResponse.firstName} ${coolPersonResponse.lastName}",
                fontSize = 20.sp,

                )
            Text(
                text = coolPersonResponse.favoriteColor,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.35f))
        Button(
            onClick = {
                viewModel.likePerson(coolPersonResponse.id)
            }
        ) {
            if (coolPersonResponse.isLiked == true) {
                Text(stringResource(id = R.string.like_button_text))
            } else {
                Text(stringResource(id = R.string.like_button_text))
            }
        }
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
            favoriteColor = "Cerulean",
            isLiked = false,
        ),
        viewModel = viewModel(),
    )
}