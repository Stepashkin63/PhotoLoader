package ru.stepashkin.picsumloader.retrofit

class MainRepository(private val picSumApi: PicSumApi) {

    suspend fun getPhotos() = picSumApi.getPhotos()
}