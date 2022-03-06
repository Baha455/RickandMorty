package com.example.rickandmorty.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.Locations
class DiffCallBackForChar: DiffUtil.ItemCallback<Characters>() {

    override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem.id ==newItem.id

    }

    override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem == newItem
    }
}

class DiffCallBackForLoc: DiffUtil.ItemCallback<Locations>() {

    override fun areItemsTheSame(oldItem: Locations, newItem: Locations): Boolean {
        return oldItem.id ==newItem.id

    }

    override fun areContentsTheSame(oldItem: Locations, newItem: Locations): Boolean {
        return oldItem == newItem
    }
}

class DiffCallBackForEP: DiffUtil.ItemCallback<Episodes>() {

    override fun areItemsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
        return oldItem.id ==newItem.id

    }

    override fun areContentsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
        return oldItem == newItem
    }
}

class DiffCallBackForCharAd: DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem

    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }
}


