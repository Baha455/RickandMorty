package com.example.rickandmorty.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.models.Characters
import java.util.*

class DiffCallBack: DiffUtil.ItemCallback<Characters>() {

    override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem.id ==newItem.id

    }

    override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem == newItem
    }
}
/*open class Append(){
    fun appendList(list: List<Any>, listAdapter: CharAdapter.MyViewHolder) {
        val currentList = listAdapter.currentList.toMutableList()
        currentList.clear()
        currentList.addAll(list)
        listAdapter.submitList(currentList)
    }
}*/

