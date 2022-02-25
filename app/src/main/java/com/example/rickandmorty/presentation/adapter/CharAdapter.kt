package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharItemBinding
import com.example.rickandmorty.models.Characters
import com.squareup.picasso.Picasso


class CharAdapter() : ListAdapter<Characters, CharAdapter.MyViewHolder>(diffCallback) {

    var onShopItemClickListener: ((Characters) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CharItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Characters>() {
            override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun appendList(list: List<Characters>) {
        val currentList = currentList.toMutableList() // get the current adapter list as a mutated list
        currentList.clear()
        currentList.addAll(list)
        submitList(currentList)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val char =getItem(position)
        Picasso.get().load(char.image).into(holder.charImage)
        holder.charName.text = char.name
        holder.locationName.text = char.location.name
        holder.species.text = char.species
        holder.status.text = char.status
        holder.cardLayout.setOnClickListener{
            onShopItemClickListener?.invoke(char)
        }

    }

    class MyViewHolder(private val binding: CharItemBinding): RecyclerView.ViewHolder(binding.root){
        val charImage = binding.charImage
        val charName = binding.charName
        val status = binding.status
        val species = binding.species
        val locationName = binding.locationName
        val episodeName = binding.episodeName
        val cardLayout = binding.cardLayout
        }
}
