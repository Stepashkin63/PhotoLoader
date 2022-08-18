package ru.stepashkin.picsumloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import ru.stepashkin.picsumloader.databinding.ActivityMainBinding
import ru.stepashkin.picsumloader.fragments.FragmentsViewModel
import ru.stepashkin.picsumloader.fragments.PhotosFragment
import ru.stepashkin.picsumloader.retrofit.MainRepository
import ru.stepashkin.picsumloader.retrofit.PicSumApi


class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    private lateinit var viewModel: FragmentsViewModel

    var tabTitle = arrayOf("Photos", "Favourite")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val picSumApi = PicSumApi.getRetrofit()
        val mainRepository = MainRepository(picSumApi)

        viewModel = ViewModelProvider(this,
            ViewModelFactory(mainRepository))[FragmentsViewModel::class.java]

        setData()

        TabLayoutMediator(bind.tabLayout, bind.viewPager2) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        //открытие фрагмента
        supportFragmentManager.beginTransaction()
            .replace(R.id.recyclerView2, PhotosFragment.).commit()
    }

    private fun setData() {
        bind.viewPager2.adapter = ViewPageAdapter(supportFragmentManager, lifecycle)

        viewModel.loading.observe(this) {
            if (it) {
                bind.progressDialog.visibility = View.VISIBLE
            } else {
                bind.progressDialog.visibility = View.GONE
            }
        }
    }
}