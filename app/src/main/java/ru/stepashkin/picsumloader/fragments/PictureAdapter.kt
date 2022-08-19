package ru.stepashkin.picsumloader.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.stepashkin.picsumloader.databinding.AdapterBinding
import ru.stepashkin.picsumloader.model.ModelPhotosItem

class PictureAdapter : RecyclerView.Adapter<PictureAdapter.MainViewHolder>() {

    private var photoList = mutableListOf<ModelPhotosItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setPhotos(photos: List<ModelPhotosItem>) {
        this.photoList = photos.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val body = photoList[position]

        holder.binding.name.text = body.author

        Glide.with(holder.itemView.context).load(body.download_url).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int = photoList.size

    class MainViewHolder(val binding: AdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PhotosFragment, listener: ContentListener) {
            itemView.setOnClickListener {
                listener.onItemClicked(data)
            }
        }
    }

    interface ContentListener {
        fun onItemClicked(item: PhotosFragment)
    }
}