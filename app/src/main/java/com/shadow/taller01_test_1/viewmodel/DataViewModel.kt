package com.shadow.taller01_test_1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shadow.taller01_test_1.data.objectList
import com.shadow.taller01_test_1.model.ObjectClass
import kotlinx.coroutines.launch

class DataViewModel: ViewModel(){
    fun addData(myObjectClass: ObjectClass) {
        viewModelScope.launch {
            objectList.value.add(myObjectClass)
        }
    }

    fun getData(): MutableList<ObjectClass> {
        return objectList.value
    }
    /*TO DO: Actualizar y Eliminar*/

}