package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.EpisodesItemBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.Episodes
import java.util.*

class EpisodeAdapter() : ListAdapter<Episodes, EpisodeAdapter.MyViewHolder>(DiffCallBackForEP()) {

    private var fullList = mutableListOf<Episodes>()
    var onItemClickListener: ((Episodes) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            EpisodesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    fun modifyList(list : List<Episodes>) {
        fullList = list.toMutableList()
        submitList(list)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val episode =getItem(position)
        holder.airDate.text = episode.air_date
        holder.episode.text = episode.episode
        holder.cardLayout.setOnClickListener{
            onItemClickListener?.invoke(episode)
        }
    }

    class MyViewHolder(binding: EpisodesItemBinding): RecyclerView.ViewHolder(binding.root){
        val airDate = binding.airDate
        val episode = binding.episode
        val cardLayout = binding.cardLayout
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<Episodes>()

        if(!query.isNullOrEmpty()) {
            list.addAll(fullList.filter {
                it.episode.lowercase().contains(query.toString().lowercase())
            })

        } else {
            list.addAll(fullList)
        }
        submitList(list)
    }
}