package com.example.rickandmorty.presentation.fragments.locations

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.databinding.LocationFragmentBinding
import com.example.rickandmorty.models.Locations
import com.example.rickandmorty.presentation.adapter.CharAdapter2
import com.example.rickandmorty.presentation.adapter.LocationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment: Fragment() {

    private lateinit var binding: LocationFragmentBinding
    private val viewModel by viewModels<LocationsViewModel>()
    private val adapter by lazy { LocationsAdapter() }
    var page = 1
    lateinit var parameter: String
    var url: Uri? = null
    var pages: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LocationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        getLocations()
        initListener()
        context?.let { viewModel.setupSpinnerAdapter(binding.spinner, it) }
        viewModel.spinner(binding.spinner, binding.searchTv)
    }

    private fun initRv(){
        viewModel.locations.observe(viewLifecycleOwner,{
            adapter.modifyList(it.results)
        })
        binding.locationsRv.adapter = adapter
    }


    private fun getLocations() {
        binding.btnSearch.setOnClickListener{
            val etText = binding.searchTv.text.toString()
            viewModel.parameter.observe(viewLifecycleOwner, {
                parameter = it.toString()
            })
            when(parameter){
                "0" -> Toast.makeText(context, "Выберите параметр", Toast.LENGTH_SHORT).show()
                "1" -> viewModel.searchByName(etText)
                "2" -> viewModel.searchByType(etText)
                "3" -> viewModel.searchByDimension(etText)
            }
        }

        binding.btnFilter.setOnClickListener{
            context?.let { it1 -> viewModel.showDialog(it1, layoutInflater) }
            filterList()
        }

        binding.btnNext.setOnClickListener{
            viewModel.getByUrl("https://rickandmortyapi.com/api/location".toUri())
        }
    }

    private fun initListener(){
        adapter.onItemClickListener = {
            toDetailLocations(it as Locations)
        }
    }

    private fun toDetailLocations(locations: Locations){
        val destination = LocationsFragmentDirections.actionLocationsFragmentToLocationDetailFragment(locations)
        findNavController().navigate(destination)
    }

    private fun filterList(){
        viewModel.parameterFilter.observe(viewLifecycleOwner,{
            adapter.filter(it)
        })
    }
}