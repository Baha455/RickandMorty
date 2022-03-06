package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharItemBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.Locations
import com.squareup.picasso.Picasso
import java.util.*


open class CharAdapter() : ListAdapter<Characters, CharAdapter.MyViewHolder>(DiffCallBackForChar()) {
    private var fullList = mutableListOf<Characters>()

    var onItemClickListener: ((Characters) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CharItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    fun modifyList(list : List<Characters>) {
        fullList = list.toMutableList()
        submitList(list)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val char =getItem(position)
        Picasso.get().load(char.image).into(holder.charImage)
        holder.charName.text = char.name
        holder.locationName.text = char.location.name
        holder.species.text = char.species
        holder.status.text = char.status
        holder.cardLayout.setOnClickListener{
            onItemClickListener?.invoke(char)
        }

    }

    class MyViewHolder(private val binding: CharItemBinding): RecyclerView.ViewHolder(binding.root){
        val charImage = binding.charImage
        val charName = binding.charName
        val status = binding.status
        val species = binding.species
        val locationName = binding.locationName
        val cardLayout = binding.cardLayout
        }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<Characters>()

        if(!query.isNullOrEmpty()) {
            list.addAll(fullList.filter {
                it.status.lowercase().contains(query.toString().lowercase()) ||
                        it.gender.lowercase().contains(query.toString().lowercase()) ||
                        it.species.lowercase().contains(query.toString().lowercase())

            })

        } else {
            list.addAll(fullList)
        }

        submitList(list)
    }


}


