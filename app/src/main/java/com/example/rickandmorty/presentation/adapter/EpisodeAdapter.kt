package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.EpisodesItemBinding
import com.example.rickandmorty.models.Episodes

class EpisodeAdapter(private val listener: (Episodes) -> Unit) : RecyclerView.Adapter<EpisodeAdapter.MyViewHolder>() {

    var items1 = arrayListOf<Episodes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            EpisodesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    fun updateData(list: List<Episodes>){
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

    class MyViewHolder(
        private val binding: EpisodesItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(episodes: Episodes, listener: (list: Episodes) -> Unit){
            binding.airDate.text = episodes.air_date
            binding.episode.text = episodes.name


        }
    }
}