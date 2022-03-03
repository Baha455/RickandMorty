package com.example.rickandmorty.presentation.fragments.locations

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.LocationFragmentBinding
import com.example.rickandmorty.models.Locations
import com.example.rickandmorty.presentation.adapter.LocationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment: Fragment() {

    private lateinit var binding: LocationFragmentBinding
    private val viewModel by viewModels<LocationsViewModel>()
    private val adapter by lazy { LocationsAdapter()}
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
            adapter.appendList(it.results)
        })
        binding.locationsRv.adapter = adapter
    }


    private fun getLocations() {
        viewModel.locations.observe(viewLifecycleOwner,{
            pages = it.info.pages
        })
        binding.btnNext.setOnClickListener {
            if (page ==pages) {
                Toast.makeText(context, "Вы на последней странице", Toast.LENGTH_SHORT).show()
            }else{
                page++
                nextPage()
            }
        }
        binding.btnPrev.setOnClickListener {
            if (page <= 1) {
                Toast.makeText(context, "Вы на первой странице", Toast.LENGTH_SHORT).show()
            }else{
                page--
                prevPage()
            }
        }
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
    }

    private fun nextPage(){
        viewModel.locations.observe(viewLifecycleOwner, {
            if (it.info.next != null) {
                url = it.info.next.toUri()
            } else {
                Toast.makeText(context, "Вы на последней странице", Toast.LENGTH_SHORT).show()
            }
        })
        url?.let { viewModel.getByUrl(it) }
        Toast.makeText(context, "Новые страницы загружены", Toast.LENGTH_SHORT).show()
    }

    private fun prevPage(){
        viewModel.locations.observe(viewLifecycleOwner,{
            if (it.info.prev!= null) {
                url = it.info.prev.toUri()
            }else{
                Toast.makeText(context, "Вы на первой странице", Toast.LENGTH_SHORT).show()
            }
        })
        if(url!= null) {
            viewModel.getByUrl(url!!)
        }
    }

    private fun initListener(){
        adapter.onShopItemClickListener = {
            toDetailLocations(it)
        }
    }

    private fun toDetailLocations(locations: Locations){
        val destination = LocationsFragmentDirections.actionLocationsFragmentToLocationDetailFragment(locations)
        findNavController().navigate(destination)
    }
}