package ru.stepashkin.picsumloader.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.stepashkin.picsumloader.R
import ru.stepashkin.picsumloader.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    private var _bind: FragmentFavouriteBinding? = null
    private val bind get() = _bind!!

    private var pictureAdapter = PictureAdapter()

    private val fragmentsViewModel: FragmentsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _bind = FragmentFavouriteBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentsViewModel.favouriteData.observe(viewLifecycleOwner) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}