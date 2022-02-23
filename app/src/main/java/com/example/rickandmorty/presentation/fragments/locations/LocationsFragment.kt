package com.example.rickandmorty.presentation.fragments.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.LocationFragmentBinding
import com.example.rickandmorty.presentation.adapter.LocationsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationsFragment: Fragment() {

    private lateinit var binding: LocationFragmentBinding
    private val viewModel by viewModel<LocationsViewModel>()
    private val adapter by lazy { LocationsAdapter(){toDetailLocations()} }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = LocationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLocations()
        initVm()
        initRv()
    }

    private fun initVm(){
        viewModel.locations.observe(viewLifecycleOwner,{
            adapter.updateData(it)
        })
    }

    private fun initRv(){
        binding.locationsRv.adapter = adapter
    }



    private fun toDetailLocations(){

    }
}