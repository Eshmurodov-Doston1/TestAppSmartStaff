package com.example.testappsmartstaff.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testappsmartstaff.models.Result

class AppViewModel(var position:Int):ViewModel() {
    var textMy:String?=null

    private val positionMy = MutableLiveData<Int>().apply {
        value = position
    }
    var progress:LiveData<Int> = positionMy

    private val text = MutableLiveData<String>().apply {
        value = textMy
    }
    var text1:LiveData<String> = text


}