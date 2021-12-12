package com.khedr.rickandmorty.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khedr.rickandmorty.databinding.ItemCharacterBinding
import com.khedr.rickandmorty.domain.model.Character

class CharactersAdapter(val context: Context) :
    RecyclerView.Adapter<CharactersAdapter.CharacterVH>() {

    var itemClickListener: ItemClickListener? = null

    var charactersList: List<Character> = emptyList()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterVH(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        val character = charactersList[position]
        holder.b.tvCharacterName.text = character.name
        Glide.with(context).load(character.image).timeout(30000).into(holder.b.ivCharacterItem)
    }

    override fun getItemCount(): Int = charactersList.size

    inner class CharacterVH(
        var b: ItemCharacterBinding
    ) :
        RecyclerView.ViewHolder(b.root) {
        init {
            b.parentItemCharacter.setOnClickListener {
                itemClickListener!!.onItemClicked(position = adapterPosition)
            }
        }
    }
}