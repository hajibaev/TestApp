package com.example.testapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.DoorItemBinding
import com.example.testapp.presentation.models.DoorUiModel
import com.example.testapp.presentation.utils.runSwipeAnimation
import com.example.testapp.presentation.utils.show
import com.example.testapp.presentation.utils.showRoundedImage
import com.example.testapp.presentation.utils.swipeAnim

class DoorAdapter(private val listener: DoorItemClickListener) :
    ListAdapter<DoorUiModel, DoorAdapter.ViewHolder>(DoorDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.door_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDoor(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val doorBinding = DoorItemBinding.bind(itemView)

        fun bindDoor(door: DoorUiModel) = doorBinding.apply {
            if (door.favorites == true) {
                favoritesPoster.setImageResource(R.drawable.star_yellow)
            } else favoritesPoster.setImageResource(R.drawable.star)

            if (door.room == null) {
                lock.setImageResource(R.drawable.lockoff)
            } else lock.setImageResource(R.drawable.lockon)

            containerItem.swipeAnim(
                isCamera = false,
                itemView = containerItem,
                view = favoritesContainer,
                editView = editContainer
            )

            title.text = door.name
            
            if (door.snapshot != null) {
                posterCamera.showRoundedImage(imageUrl = door.snapshot)
                posterCamera.show()
                playIcon.show()
            }
            favoritesContainer.setOnClickListener {
                runSwipeAnimation(
                    -280f, 0f, itemView = containerItem, animView = favoritesContainer
                )
            }
            editContainer.setOnClickListener {
                listener.doorItemOnClick(door.id)
            }
        }
    }
}


interface DoorItemClickListener {

    fun doorItemOnClick(id: Int)

}


class DoorDiffCallBack : DiffUtil.ItemCallback<DoorUiModel>() {

    override fun areItemsTheSame(oldItem: DoorUiModel, newItem: DoorUiModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DoorUiModel, newItem: DoorUiModel) = oldItem == newItem

}