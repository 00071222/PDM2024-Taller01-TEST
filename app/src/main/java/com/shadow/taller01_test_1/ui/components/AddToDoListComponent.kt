package com.shadow.taller01_test_1.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shadow.taller01_test_1.model.ObjectClass
import com.shadow.taller01_test_1.viewmodel.DataViewModel
import java.time.Instant
import java.time.ZoneId

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDoListComponent(onClick: () -> Unit,  context: Context) {
    val textFieldTitle: MutableState<String> = remember { mutableStateOf("") }
    val textFieldDescription: MutableState<String> = remember { mutableStateOf("") }
    val viewModel = DataViewModel()
    val stateOne = rememberDatePickerState()
    val stateTwo = rememberDatePickerState()
    var showCalendarOne by remember {
        mutableStateOf(false)
    }
    var showCalendarTwo by remember {
        mutableStateOf(false)
    }
    var isButtonEnabled by remember { mutableStateOf(false) }
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
        OutlinedButton(onClick = { showCalendarOne = true }) {
            Text(text = "Select first date")
        }
        if (showCalendarOne) {
            DatePickerDialog(
                onDismissRequest = { showCalendarOne = false },
                confirmButton = {
                    Button(onClick = { showCalendarOne = false }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showCalendarOne = false }) {
                        Text(text = "Cancel")
                    }
                }) {
                DatePicker(state = stateOne)
            }
        }
        val dateOne = stateOne.selectedDateMillis
        var dateOneX: String? = ""
        dateOne?.let {
            val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
            dateOneX = "${localDate.dayOfMonth}/${localDate.monthValue}/${localDate.year}"
            Text(text = "Date ONE: $dateOneX")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedButton(onClick = { showCalendarTwo = true }) {
            Text(text = "Select second date")
        }
        if (showCalendarTwo) {
            DatePickerDialog(
                onDismissRequest = { showCalendarTwo = false },
                confirmButton = {
                    Button(onClick = { showCalendarTwo = false }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showCalendarTwo = false }) {
                        Text(text = "Cancel")
                    }
                }) {
                DatePicker(state = stateTwo)
            }
        }
        val dateTwo = stateTwo.selectedDateMillis
        var dateTwoX: String? = ""
        dateTwo?.let {
            val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
            dateTwoX = "${localDate.dayOfMonth}/${localDate.monthValue}/${localDate.year}"
            Text(text = "Date Two: $dateTwoX")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        //Validation of add button

        Button(
            onClick = {
                if (textFieldTitle.value.isNotBlank() &&
                    textFieldDescription.value.isNotBlank() &&
                    dateOneX.toString().isNotBlank() &&
                    dateTwoX.toString().isNotBlank()
                ) {
                    val data = ObjectClass(
                        title = textFieldTitle.value,
                        description = textFieldDescription.value,
                        startDate = dateOneX.toString(),
                        endDate = dateTwoX.toString()
                    )
                    viewModel.addData(data)
                    onClick()
                } else {
                    Toast.makeText(context,"Hello",Toast.LENGTH_SHORT ).show()
                }

            },
            //enabled = isButtonEnabled,
        ) {
            Text(text = "Add")
        }
    }
}

@Preview
@Composable
private fun ComponentPreview() {
    AddToDoListComponent(onClick = {}, context = LocalContext.current)
}
