package com.example.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharItemBinding
import com.example.rickandmorty.models.Characters
import com.squareup.picasso.Picasso


class CharAdapter(private val listener: (Characters) -> Unit) : RecyclerView.Adapter<CharAdapter.MyViewHolder>() {

    var items1 = arrayListOf<Characters>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CharItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    fun updateData(list: List<Characters>){
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
    private val binding: CharItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(characters: Characters, listener: (list: Characters) -> Unit){
            Picasso.get()
                .load(characters.image)
                .into(binding.charImage)
            binding.charName.text = characters.name
            binding.status.text = characters.status
            binding.species.text = characters.species
            binding.locationName.text = characters.location.name
            binding.cardLayout.setOnClickListener{
                listener.invoke(characters)
            }

            }
        }
}