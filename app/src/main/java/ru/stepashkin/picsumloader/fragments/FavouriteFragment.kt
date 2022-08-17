package ru.stepashkin.picsumloader.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import ru.stepashkin.picsumloader.R
import ru.stepashkin.picsumloader.databinding.FragmentPhotosBinding

class FavouriteFragment : Fragment() {

    private var _bind: FragmentPhotosBinding? = null
    private val bind get() = _bind!!

    private var adapter = PictureAdapter()

    private val fragmentViewModel: FragmentsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}