package ru.stepashkin.picsumloader.retrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.stepashkin.picsumloader.model.ModelPhotosItem
import ru.stepashkin.picsumloader.const.BASE_URL
import ru.stepashkin.picsumloader.const.END_POINT_GET

interface PicSumApi {

    //Делаем запрос
    @GET(END_POINT_GET)
    suspend fun getPhotos(): Response<List<ModelPhotosItem>>

    companion object {
        var picSumApi: PicSumApi? = null
        fun getRetrofit(): PicSumApi {
            if (picSumApi == null) {
                picSumApi = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PicSumApi::class.java)

            }
            return picSumApi!!
        }
    }
}
