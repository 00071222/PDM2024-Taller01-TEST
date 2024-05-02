package com.shadow.taller01_test_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.shadow.taller01_test_1.ui.theme.Taller01TEST1Theme

class LoginActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Taller01TEST1Theme {
                Text(text = "HOLA LOGIN")
            }
        }
    }
}