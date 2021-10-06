package com.example.testappsmartstaff.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testappsmartstaff.models.Result

class ResultViewModel(var width:Int, var height:Int):ViewModel() {


    private val widthMy = MutableLiveData<Result>().apply {
        value = Result(width,height)
    }
    var MyResult:LiveData<Result> = widthMy

}