package com.example.rickandmorty.presentation.fragments.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.databinding.LocationFragmentBinding
import com.example.rickandmorty.models.Locations
import com.example.rickandmorty.presentation.adapter.LocationsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationsFragment: Fragment() {

    private lateinit var binding: LocationFragmentBinding
    private val viewModel by viewModel<LocationsViewModel>()
    private val adapter by lazy { LocationsAdapter()}
    var page = 1

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
        viewModel.getLocations(page)
        initVm()
        initRv()
        getLocations()
        initListener()
    }

    private fun initVm(){
        viewModel.locations.observe(viewLifecycleOwner,{
            adapter.appendList(it)
        })
    }

    private fun initRv(){
        binding.locationsRv.adapter = adapter
    }

    private fun getLocations(){
        binding.btnNext.setOnClickListener {
            if (page == 42) {
                Toast.makeText(context, "Вы на последней странице", Toast.LENGTH_SHORT).show()
            }else{
                nextPage()
            }
        }
        binding.btnPrev.setOnClickListener {
            if (page <= 1) {
                Toast.makeText(context, "Вы на первое странице", Toast.LENGTH_SHORT).show()
            }else{
                prevPage()
            }
        }
    }

    private fun nextPage(){
        page++
        viewModel.getLocations(page)
        Toast.makeText(context, "Новые страницы загружены", Toast.LENGTH_SHORT).show()
    }

    private fun prevPage(){
        page--
        viewModel.getLocations(page)
        Toast.makeText(context, "Предыдущие страницы загружены", Toast.LENGTH_SHORT).show()
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