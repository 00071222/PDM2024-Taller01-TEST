package com.shadow.taller01_test_1.ui.screens


import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shadow.taller01_test_1.AddToListActivity
import com.shadow.taller01_test_1.EditActivity
import com.shadow.taller01_test_1.MainActivity
import com.shadow.taller01_test_1.model.ObjectClass
import com.shadow.taller01_test_1.ui.components.ToDoCard
import com.shadow.taller01_test_1.viewmodel.DataViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun HomeScreen(context: Context) {
    val viewModel = DataViewModel()
    val listShared: MutableState<MutableList<ObjectClass>> =
        remember { mutableStateOf(mutableListOf()) }
    listShared.value = viewModel.getData()
    val loadingState: MutableState<Boolean> = remember { mutableStateOf(false) }
    val intentMain = Intent(context, MainActivity::class.java)
    val intentAdd = Intent(context, AddToListActivity::class.java)
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "To Do List") }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                IconButton(
                    onClick = {
                        context.startActivity(intentMain)
                    },
                    modifier = Modifier
                        .weight(25f)
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Default.Home, contentDescription = "Home", Modifier.size(50.dp))
                }
                IconButton(
                    onClick = {
                        context.startActivity(intentAdd)
                    },
                    modifier = Modifier
                        .weight(25f)
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", Modifier.size(50.dp))
                }
                IconButton(
                    onClick = {

                        GlobalScope.launch(Dispatchers.IO) {
                            loadingState.value = true
                            viewModel.deleteAllData()
                            listShared.value = viewModel.getData()
                            loadingState.value = true
                        }
                    },
                    modifier = Modifier
                        .weight(25f)
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", Modifier.size(50.dp))
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                context.startActivity(Intent(context, AddToListActivity::class.java))
                (context as Activity)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        if (loadingState.value) {
            if (listShared.value.isEmpty())
                Text(text = "No data")
            else
                CircularProgressIndicator()
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
            ) {
                itemsIndexed(listShared.value) { index, item ->
                    ToDoCard(
                        title = item.title,
                        description = item.description,
                        start = item.startDate,
                        end = item.endDate,
                        onClick = {
                            val intent = Intent(context, EditActivity::class.java)
                            intent.putExtra("index", index)
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    HomeScreen(context = LocalContext.current)
}