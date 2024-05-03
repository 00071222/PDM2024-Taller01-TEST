package com.shadow.taller01_test_1.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.shadow.taller01_test_1.MainActivity
import com.shadow.taller01_test_1.ui.components.EditToDoListComponent
import com.shadow.taller01_test_1.ui.components.NavToMainComponent

@Composable
fun EditToDoListScreen(context: Context) {
    Column {
        NavToMainComponent {
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as Activity)
        }
        EditToDoListComponent(
            onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
                (context as Activity)
            },
            context = context
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    EditToDoListScreen(context = LocalContext.current)
}
