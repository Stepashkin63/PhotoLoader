package ru.stepashkin.picsumloader.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.stepashkin.picsumloader.fragments.R
import ru.stepashkin.picsumloader.R

class PhotosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PhotosFragment()
    }
}