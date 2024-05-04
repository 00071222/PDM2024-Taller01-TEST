package com.shadow.taller01_test_1.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.shadow.taller01_test_1.model.ObjectClass

val objectList: MutableState<MutableList<ObjectClass>> = mutableStateOf(mutableListOf())