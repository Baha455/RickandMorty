package com.example.rickandmorty.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.Locations

interface Equatable {
    override fun equals(other: Any?): Boolean
}
class DiffCallBackForCharAd1: DiffUtil.ItemCallback<Equatable>() {

    override fun areItemsTheSame(oldItem: Equatable, newItem: Equatable): Boolean {
        return when{
            oldItem is Characters && newItem is Characters ->{
                oldItem.id == newItem.id
            }
            oldItem is Locations && newItem is Characters -> {
                oldItem.id == newItem.id
            }
            oldItem is Episodes && newItem is Episodes -> {
                oldItem.id == newItem.id
            }
            else -> false
        }

    }

    override fun areContentsTheSame(oldItem: Equatable, newItem: Equatable): Boolean {
        return when{
            oldItem is Characters && newItem is Characters ->{
                oldItem == newItem
            }
            oldItem is Locations && newItem is Characters -> {
                oldItem == newItem
            }
            oldItem is Episodes && newItem is Episodes -> {
                oldItem == newItem
            }
            else -> false
        }
    }
}



