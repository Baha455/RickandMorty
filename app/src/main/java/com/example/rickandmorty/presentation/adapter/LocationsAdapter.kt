package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.LocationsItemBinding
import com.example.rickandmorty.models.Locations

class LocationsAdapter(private val listener: (Locations) -> Unit) : RecyclerView.Adapter<LocationsAdapter.MyViewHolder>() {

    var items1 = arrayListOf<Locations>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            LocationsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    fun updateData(list: List<Locations>){
        this.items1.clear()
        this.items1.addAll(list)
        notifyDataSetChanged()


    }

    override fun getItemCount(): Int {
        return items1.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items1[position], listener)
    }

    class MyViewHolder(private val binding: LocationsItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(locations: Locations, listener: (Locations) -> Unit){
           binding.planetName.text = locations.name
            binding.specific.text = locations.type
            binding.dimensionName.text = locations.dimension

        }
    }
}