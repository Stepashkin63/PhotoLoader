package ru.stepashkin.picsumloader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.stepashkin.picsumloader.retrofit.MainRepository

class ViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            MainActivityViewModel(repository) as T
        } else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}