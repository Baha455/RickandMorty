package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.EpisodesItemBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.Episodes

class EpisodeAdapter() : ListAdapter<Episodes, EpisodeAdapter.MyViewHolder>(diffCallback) {

    var onShopItemClickListener: ((Episodes) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            EpisodesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Episodes>() {
            override fun areItemsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun appendList(list: List<Episodes>) {
        val currentList = currentList.toMutableList()
        currentList.clear()
        currentList.addAll(list)
        submitList(currentList)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val episode =getItem(position)
        holder.airDate.text = episode.air_date
        holder.episode.text = episode.episode
        holder.cardLayout.setOnClickListener{
            onShopItemClickListener?.invoke(episode)
        }
    }

    class MyViewHolder(binding: EpisodesItemBinding): RecyclerView.ViewHolder(binding.root){
        val airDate = binding.airDate
        val episode = binding.episode
        val cardLayout = binding.cardLayout
    }
}