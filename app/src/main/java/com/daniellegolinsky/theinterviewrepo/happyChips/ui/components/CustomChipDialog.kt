package com.daniellegolinsky.theinterviewrepo.happyChips.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.daniellegolinsky.theinterviewrepo.R

@Composable
fun AddChipDialog(
    onAdd: (String) -> Unit,
    onCancel: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onCancel() }
    ) {
        val newTag = remember { mutableStateOf("") }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.add_other),
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )
            
            TextField(value = newTag.value, onValueChange = { newTag.value = it })

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                TextButton(
                    onClick = { onCancel() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Dismiss")
                }
                TextButton(
                    onClick = {
                        onAdd(newTag.value)
                        onCancel()
                    },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}
