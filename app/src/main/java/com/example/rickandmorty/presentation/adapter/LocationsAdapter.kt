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

class LocationsAdapter(): ListAdapter<Locations, LocationsAdapter.MyViewHolder>(diffCallback) {

    var onShopItemClickListener: ((Locations) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            LocationsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Locations>() {
            override fun areItemsTheSame(oldItem: Locations, newItem: Locations): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Locations, newItem: Locations): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun appendList(list: List<Locations>) {
        val currentList = currentList.toMutableList()
        currentList.clear()
        currentList.addAll(list)
        submitList(currentList)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val locations =getItem(position)
        holder.dimensionName.text = locations.dimension
        holder.planetName.text = locations.name
        holder.specificName.text = locations.type
        holder.cardLayout.setOnClickListener{
            onShopItemClickListener?.invoke(locations)
        }
    }

    class MyViewHolder(private val binding: LocationsItemBinding): RecyclerView.ViewHolder(binding.root){
        val planetName = binding.planetName
        val dimensionName = binding.dimensionName
        val specificName = binding.specific
        val cardLayout = binding.cardLayout
    }
}