package com.khedr.rickandmorty.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khedr.rickandmorty.databinding.ItemTagBinding

class FlexAdapter : RecyclerView.Adapter<FlexAdapter.TagVH>() {
    var contentList: List<String> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TagVH(
            ItemTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TagVH, position: Int) {
        holder.b.tvTag.text = contentList[position]
    }

    override fun getItemCount(): Int = contentList.size

    class TagVH(var b: ItemTagBinding) : RecyclerView.ViewHolder(b.root)

}