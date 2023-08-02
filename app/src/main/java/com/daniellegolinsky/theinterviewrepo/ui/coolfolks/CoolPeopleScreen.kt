package com.daniellegolinsky.theinterviewrepo.ui.coolfolks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniellegolinsky.theinterviewrepo.ui.components.CoolDivider
import com.daniellegolinsky.theinterviewrepo.ui.components.PersonItem

@Composable
fun CoolPeopleScreen(
    viewModel: CoolPeopleViewModel,
    modifier: Modifier
) {
    val viewState = viewModel.coolPeopleViewState.collectAsState().value

    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = "Cool people's favorite colors:",
            fontSize = 24.sp,
            modifier = Modifier.semantics { heading() }
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn {
            items(viewState.coolPeople) {
                PersonItem(it)
                CoolDivider()
            }
        }
    }
}