package com.shadow.taller01_test_1.ui.components

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shadow.taller01_test_1.data.objectList
import com.shadow.taller01_test_1.model.ObjectClass
import com.shadow.taller01_test_1.viewmodel.DataViewModel


@Composable
fun EditToDoListComponent(onClick: () -> Unit, context: Context) {
    if (context is Activity) {
        val index = context.intent.getIntExtra("index", -1)

        val item = objectList.value.getOrNull(index)
        val oldData = ObjectClass(
            title = item?.title ?: "",
            description = item?.description ?: "",
            startDate = item?.startDate ?: "",
            endDate = item?.endDate ?: ""
        )
        val textFieldTitle: MutableState<String> =
            remember { mutableStateOf(item?.title ?: "") }
        val textFieldDescription: MutableState<String> =
            remember { mutableStateOf(item?.description ?: "") }
        val textFieldStartDate: MutableState<String> =
            remember { mutableStateOf(item?.startDate ?: "") }
        val textFieldEndDate: MutableState<String> =
            remember { mutableStateOf(item?.endDate ?: "") }
        val viewModel = DataViewModel()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = textFieldTitle.value,
                onValueChange = { textFieldTitle.value = it },
                placeholder = {
                    Text(text = "Insert title")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = textFieldDescription.value,
                onValueChange = { textFieldDescription.value = it },
                placeholder = {
                    Text(text = "Insert description")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = textFieldStartDate.value,
                onValueChange = { textFieldStartDate.value = it },
                placeholder = {
                    Text(text = "Insert first date")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = textFieldEndDate.value,
                onValueChange = { textFieldEndDate.value = it },
                placeholder = { Text(text = "Insert last date") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = {
                    val newData = ObjectClass(
                        title = textFieldTitle.value,
                        description = textFieldDescription.value,
                        startDate = textFieldStartDate.value,
                        endDate = textFieldEndDate.value
                    )
                    viewModel.updateData(oldData, newData)
                    onClick()
                }) {
                Text(text = "Update")
            }
            Button(
                onClick = {
                    viewModel.deleteData(oldData)
                    onClick()
                }) {
                Text(text = "Delete")
            }
        }
    }
}


@Preview
@Composable
private fun ComponentPreview() {
    EditToDoListComponent(onClick = { }, context = LocalContext.current)
}
