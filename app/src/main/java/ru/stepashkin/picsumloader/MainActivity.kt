package ru.stepashkin.picsumloader

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import ru.stepashkin.picsumloader.databinding.ActivityMainBinding
import ru.stepashkin.picsumloader.retrofit.MainRepository
import ru.stepashkin.picsumloader.retrofit.PicSumApi

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    private val pictureAdapter = PictureAdapter()

    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val retrofitService = PicSumApi.getRetrofit()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this,
            ViewModelFactory(mainRepository))[MainActivityViewModel::class.java]

        setData()
    }

    private fun setData() {
        bind.placeHolder.adapter = pictureAdapter

        viewModel.photoData.observe(this) {
            pictureAdapter.setPhotos(it)
        }

        viewModel.loading.observe(this) {
            if (it) {
                bind.progressDialog.visibility = View.VISIBLE
            } else {
                bind.progressDialog.visibility = View.GONE
            }
        }
    }
}