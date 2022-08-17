package ru.stepashkin.picsumloader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.stepashkin.picsumloader.model.ModelPhotosItem
import ru.stepashkin.picsumloader.retrofit.MainRepository

class MainActivityViewModel(private val mainRepository: MainRepository) : ViewModel() {


    private val coroutineContext = SupervisorJob() + Dispatchers.Main.immediate
    private val coroutineScope: CoroutineScope = CoroutineScope(coroutineContext)


    private val _photosData = MutableLiveData<List<ModelPhotosItem>>()
    val photoData: LiveData<List<ModelPhotosItem>> get() = _photosData


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
                    _photosData.postValue(response.body())
                    loading.value = false
                } else {
                    loading.value = false
                }
            }
        }
    }
}