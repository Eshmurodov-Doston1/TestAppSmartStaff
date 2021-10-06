package com.example.testappsmartstaff.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory(var position:Int,var width:Int,var height:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)){
            return AppViewModel(position) as T
        }else if (modelClass.isAssignableFrom(ResultViewModel::class.java)){
            return ResultViewModel(width,height) as T
        }
        throw IllegalArgumentException("Error view model")
    }
}