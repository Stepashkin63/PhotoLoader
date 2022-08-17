package ru.stepashkin.picsumloader.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.stepashkin.picsumloader.model.ModelPhotosItem

class FragmentsViewModel : ViewModel() {
    private val _photoData = MutableLiveData<ModelPhotosItem>()
    val photoData: LiveData<ModelPhotosItem> get() = _photoData

    private val _favouriteData = MutableLiveData<ModelPhotosItem>()
    val favouriteData: LiveData<ModelPhotosItem> get() = _favouriteData


    fun setDataPhoto(data: ModelPhotosItem) {
        _photoData.value = data
    }

    fun setDataFavourite(data: ModelPhotosItem) {
        _favouriteData.value = data
    }
}