package ru.stepashkin.picsumloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import ru.stepashkin.picsumloader.databinding.ActivityMainBinding
import ru.stepashkin.picsumloader.fragments.PictureAdapter
import ru.stepashkin.picsumloader.retrofit.MainRepository
import ru.stepashkin.picsumloader.retrofit.PicSumApi

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    private val pictureAdapter = PictureAdapter()

    private lateinit var viewModel: MainActivityViewModel

    var tabTitle = arrayOf("Photos", "Favourite")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val retrofitService = PicSumApi.getRetrofit()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this,
            ViewModelFactory(mainRepository))[MainActivityViewModel::class.java]

        setData()

        bind.viewPager2.adapter = ViewPageAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(bind.tabLayout, bind.viewPager2) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    private fun setData() {
        bind.viewPager2.adapter = pictureAdapter

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