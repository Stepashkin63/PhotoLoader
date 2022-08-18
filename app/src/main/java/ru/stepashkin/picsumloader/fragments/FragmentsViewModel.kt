package ru.stepashkin.picsumloader.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.stepashkin.picsumloader.model.ModelPhotosItem
import ru.stepashkin.picsumloader.retrofit.MainRepository

class FragmentsViewModel (private val mainRepository: MainRepository): ViewModel() {

    private val coroutineContext = SupervisorJob() + Dispatchers.Main.immediate
    private val coroutineScope: CoroutineScope = CoroutineScope(coroutineContext)

    private val _photoData = MutableLiveData<List<ModelPhotosItem>>()
    val photoData: LiveData<List<ModelPhotosItem>> get() = _photoData

    private val _favouriteData = MutableLiveData<List<ModelPhotosItem>>()
    val favouriteData: LiveData<List<ModelPhotosItem>> get() = _favouriteData


    fun setDataPhoto(data: List<ModelPhotosItem>) {
        _photoData.value = data
    }

    fun setDataFavourite(data: List<ModelPhotosItem>) {
        _favouriteData.value = data
    }

    val loading = MutableLiveData<Boolean>()

    init {
        getPhotoData()
    }


    private fun getPhotoData() {
        coroutineScope.launch {
            loading.postValue(true)
            val response = mainRepository.getPhotos()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _photoData.postValue(response.body())
                    loading.value = false
                } else {
                    loading.value = false
                }
            }
        }
    }
}