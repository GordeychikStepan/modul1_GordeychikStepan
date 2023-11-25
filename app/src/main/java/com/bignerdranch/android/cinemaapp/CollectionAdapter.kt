package com.bignerdranch.android.cinemaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter для отображения коллекций
class CollectionAdapter(private val collections: List<Collection>) :
    RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    private var clickListener: OnCollectionClickListener? = null

    interface OnCollectionClickListener {
        fun onDeleteClicked(collection: Collection)
    }

    fun setOnCollectionClickListener(listener: OnCollectionClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_collection, parent, false)
        return CollectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val collection = collections[position]
        holder.bind(collection)
    }

    override fun getItemCount(): Int {
        return collections.size
    }

    inner class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
        private val deleteImageView: ImageView = itemView.findViewById(R.id.deleteImageView)

        fun bind(collection: Collection) {
            nameTextView.text = collection.name
            iconImageView.setImageResource(collection.iconId)

            deleteImageView.setOnClickListener {
                val popupMenu = PopupMenu(itemView.context, deleteImageView)
                popupMenu.inflate(R.menu.menu_delete)
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_del -> {
                            clickListener?.onDeleteClicked(collection)
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
            }
        }
    }
}
