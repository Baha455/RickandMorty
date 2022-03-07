package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharItemBinding
import com.example.rickandmorty.databinding.EpisodesItemBinding
import com.example.rickandmorty.databinding.LocationsItemBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.Locations
import com.squareup.picasso.Picasso


open class SearchAdapter() : ListAdapter<Equatable, RecyclerView.ViewHolder>(DiffCallBackForCharAd1()) {
    private var fullList = mutableListOf<Any>()

    var onItemClickListener: ((Any) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.char_item -> CharViewHolder1(
                CharItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.episodes_item -> EpViewHolder(
                EpisodesItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.locations_item -> LocViewHolder(
                LocationsItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Unsupported type")
        }

    }

    override fun getItemViewType(position: Int): Int {

        return when (fullList[position]) {

            is Characters -> R.layout.char_item

            is Episodes -> R.layout.episodes_item

            is Locations -> R.layout.locations_item

            else -> throw IllegalArgumentException("Unsupported type")
        }
    }


    fun appendList(list: List<Equatable>) {
        fullList.addAll(list)
        submitList(list)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val char = getItem(position)

        when (holder) {
            is CharViewHolder1 -> {
                val name = char as Characters
                holder.bindChar(name)
                holder.cardLayout.setOnClickListener{onItemClickListener?.invoke(name)}
            }
            is EpViewHolder -> {
                val name = char as Episodes
                holder.bindEp(name)
                holder.cardLayout.setOnClickListener{onItemClickListener?.invoke(name)}

            }
            is LocViewHolder -> {
                val name = char as Locations
                holder.bindLoc(name)
                holder.cardLayout.setOnClickListener{onItemClickListener?.invoke(name)}
            }
        }
    }
}

class CharViewHolder1(private val binding: CharItemBinding): RecyclerView.ViewHolder(binding.root) {
    val cardLayout = binding.cardLayout
    fun bindChar(item: Characters) {
        Picasso.get().load(item.image).into(binding.charImage)
        binding.charName.text = item.name
        binding.locationName.text = item.location.name
        binding.species.text = item.species
        binding.status.text = item.status
    }
}


class EpViewHolder(private val binding: EpisodesItemBinding): RecyclerView.ViewHolder(binding.root){
    val cardLayout = binding.cardLayout
        fun bindEp(item: Episodes){
            binding.airDate.text = item.air_date
            binding.episode.text = item.episode
        }
    }

class LocViewHolder(private val binding: LocationsItemBinding): RecyclerView.ViewHolder(binding.root){
    val cardLayout = binding.cardLayout
        fun bindLoc(item: Locations){
            binding.dimensionName.text = item.dimension
            binding.planetName.text = item.name
            binding.specific.text =  item.type
        }
    }






