package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.databinding.CharDetailFragmentBinding
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.presentation.adapter.EpisodeAdapter
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class CharDetailFragment:Fragment() {

    private lateinit var binding: CharDetailFragmentBinding
    private val args: CharDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = CharDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        val item = args.characters
        Picasso.get()
            .load(item.image)
            .into(binding.charImage)
        binding.firstLocationName.text =item.location.name
        binding.genderName.text = item.gender
        binding.speciesName.text = item.species
        binding.nameName.text = item.name
        binding.statusName.text = item.status
    }

}





