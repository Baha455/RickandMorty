package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.LocationsItemBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.Locations

class LocationsAdapter(): ListAdapter<Locations, LocationsAdapter.MyViewHolder>(DiffCallBackForLoc()) {

    private var fullList = mutableListOf<Locations>()
    var onItemClickListener: ((Locations) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            LocationsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    fun modifyList(list : List<Locations>) {
        fullList = list.toMutableList()
        submitList(list)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val locations =getItem(position)
        holder.dimensionName.text = locations.dimension
        holder.planetName.text = locations.name
        holder.specificName.text = locations.type
        holder.cardLayout.setOnClickListener{
            onItemClickListener?.invoke(locations)
        }
    }

    class MyViewHolder(private val binding: LocationsItemBinding): RecyclerView.ViewHolder(binding.root){
        val planetName = binding.planetName
        val dimensionName = binding.dimensionName
        val specificName = binding.specific
        val cardLayout = binding.cardLayout
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<Locations>()

        if(!query.isNullOrEmpty()) {
            list.addAll(fullList.filter {
                it.dimension.lowercase().contains(query.toString().lowercase())
            })

        } else {
            list.addAll(fullList)
        }
        submitList(list)
    }
}