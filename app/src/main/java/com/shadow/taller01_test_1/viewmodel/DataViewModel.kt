package com.shadow.taller01_test_1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shadow.taller01_test_1.data.objectList
import com.shadow.taller01_test_1.model.ObjectClass
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {
    fun addData(myObjectClass: ObjectClass) {
        viewModelScope.launch {
            objectList.value.add(myObjectClass)
        }
    }

    fun getData(): MutableList<ObjectClass> {
        return objectList.value
    }

    fun deleteData(myObjectClass: ObjectClass) {
        viewModelScope.launch {
            objectList.value.remove(myObjectClass)
        }
    }

    fun updateData(oldObjectClass: ObjectClass, newObjectClass: ObjectClass) {
        viewModelScope.launch {
            val index = objectList.value.indexOf(oldObjectClass)
            if (index != -1) {
                objectList.value[index] = newObjectClass
            }
        }
    }

    fun deleteAllData() {
        viewModelScope.launch {
            objectList.value.clear()
        }
    }
}