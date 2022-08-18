package ru.stepashkin.picsumloader.fragments

import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo.newInstance
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import ru.stepashkin.picsumloader.R
import ru.stepashkin.picsumloader.databinding.FragmentPhotosBinding

class PhotosFragment : Fragment() {

    private var _bind: FragmentPhotosBinding? = null
    private val bind get() = _bind!!

    private var pictureAdapter = PictureAdapter()

    private val fragmentsViewModel: FragmentsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _bind = FragmentPhotosBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.recyclerView1.adapter = pictureAdapter

        fragmentsViewModel.photoData.observe(viewLifecycleOwner) {
            pictureAdapter.setPhotos(it)
        }
        //нажатие на изображение
        bind.recyclerView1.setOnClickListener{
            (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.recyclerView2, FavouriteFragment.).commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}