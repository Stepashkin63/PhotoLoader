package ru.stepashkin.picsumloader

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.stepashkin.picsumloader.fragments.FavouriteFragment
import ru.stepashkin.picsumloader.fragments.PhotosFragment

class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PhotosFragment()
            1 -> FavouriteFragment()
            else -> return PhotosFragment()
        }
    }
}