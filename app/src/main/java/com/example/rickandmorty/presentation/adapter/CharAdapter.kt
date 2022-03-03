package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharItemBinding
import com.example.rickandmorty.models.Characters
import com.squareup.picasso.Picasso
import java.util.*


open class CharAdapter() : ListAdapter<Characters, CharAdapter.MyViewHolder>(DiffCallBack()) {
    private var list = mutableListOf<Characters>()
    private var fullList = mutableListOf<Characters>()
    var onItemClickListener: ((Characters) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CharItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }



    fun appendList(list: List<Characters>) {
        this.list = list.toMutableList()
        //fullList.clear()
        fullList.addAll(this.list)
        submitList(list)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val char =getItem(position)
        Picasso.get().load(char.image).into(holder.charImage)
        holder.charName.text = char.name
        holder.locationName.text = char.location.name
        holder.species.text = char.species
        holder.status.text = char.status
        holder.cardLayout.setOnClickListener{
            onItemClickListener?.invoke(char)
        }

    }

    class MyViewHolder(private val binding: CharItemBinding): RecyclerView.ViewHolder(binding.root){
        val charImage = binding.charImage
        val charName = binding.charName
        val status = binding.status
        val species = binding.species
        val locationName = binding.locationName
        val cardLayout = binding.cardLayout
        }

    fun modifyList(list : List<Characters>) {
        fullList = list.toMutableList()
        submitList(list)
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<Characters>()

        if(!query.isNullOrEmpty()) {
            list.addAll(fullList.filter {
                it.status.lowercase(Locale.getDefault()).contains(query.toString().lowercase(Locale.getDefault()))
            })

        } else {
            list.addAll(fullList)
        }

        submitList(list)
    }

    /*override fun getFilter(): Filter {
        return charFilter
    }

    private val charFilter = object : Filter(){
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Characters>()
            if (p0 == null || p0.isEmpty()){
                filteredList.clear()
                filteredList.addAll(fullList)
            }else{
                for (item in fullList){
                    if (item.status.lowercase().startsWith(p0.toString().lowercase())){
                        filteredList.add(item)
                    }
                }
            }
            val result = FilterResults()
            result.values = filteredList
            return result
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            list.clear()
            list.addAll(p1?.values as MutableList<Characters>)
            appendList(list)

        }

    }*/
}


