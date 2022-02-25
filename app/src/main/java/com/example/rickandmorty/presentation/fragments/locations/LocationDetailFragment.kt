package com.example.rickandmorty.presentation.fragments.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.databinding.LocationDetailFragmentBinding

class LocationDetailFragment:Fragment() {

    private lateinit var binding : LocationDetailFragmentBinding
    private val args: LocationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LocationDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        val item = args.locations
        binding.dimensionName.text = item.dimension
        binding.locationName.text = item.name
        binding.typeName.text = item.type
    }
}
