package com.example.testapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.ItemCameraBinding
import com.example.testapp.presentation.models.CameraUiModel
import com.example.testapp.presentation.utils.runSwipeAnimation
import com.example.testapp.presentation.utils.showRoundedImage
import com.example.testapp.presentation.utils.swipeAnim

class CameraAdapter : ListAdapter<CameraUiModel, CameraAdapter.ViewHolder>(CameraDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_camera, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCamera(getItem(position))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cameraBinding = ItemCameraBinding.bind(itemView)

        fun bindCamera(camera: CameraUiModel) = cameraBinding.apply {

            if (camera.favorites == true) {
                star.setImageResource(R.drawable.star_yellow)
                favoritesPoster.setImageResource(R.drawable.star_yellow)
            } else {
                star.setImageResource(R.drawable.star)
                favoritesPoster.setImageResource(R.drawable.star)
            }
            cardItem.swipeAnim(
                isCamera = true,
                itemView = containerItem,
                view = favoritesContainer,
                editView = null
            )
            rec.isVisible = camera.rec
            title.text = camera.name
            posterCamera.showRoundedImage(imageUrl = camera.snapshot)
            favoritesContainer.setOnClickListener {
                runSwipeAnimation(
                    -160f, 0f, itemView = containerItem, animView = favoritesContainer
                )
            }
        }
    }

}

class CameraDiffCallBack : DiffUtil.ItemCallback<CameraUiModel>() {

    override fun areItemsTheSame(oldItem: CameraUiModel, newItem: CameraUiModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CameraUiModel, newItem: CameraUiModel) =
        oldItem == newItem

}