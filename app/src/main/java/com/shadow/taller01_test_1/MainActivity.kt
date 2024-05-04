package com.shadow.taller01_test_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.shadow.taller01_test_1.ui.screens.HomeScreen
import com.shadow.taller01_test_1.ui.theme.Taller01TEST1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Taller01TEST1Theme {
                HomeScreen(context = LocalContext.current)
            }
        }
    }
}
