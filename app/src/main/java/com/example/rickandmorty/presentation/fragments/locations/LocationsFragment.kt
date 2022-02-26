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
        setupSpinnerAdapter()
        spinner()
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
            url = it.info.next.toUri()
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

    private fun setupSpinnerAdapter() {
        context?.let {
            ArrayAdapter.createFromResource(
                it.applicationContext,
                R.array.parameterLoc,
                R.layout.customtxt
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.dropdown_spinner)
                binding.spinner.adapter = adapter
            }
        }
    }

    private fun spinner(){
        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0 -> {parameter = "0"
                        binding.searchTv.hint = "Выберите параметр"}
                    1 -> {parameter = "1"
                        binding.searchTv.hint = "Имя"
                    }
                    2 -> {parameter = "2"
                        binding.searchTv.hint = "Space station, Resort, Planet" }
                    3 -> {parameter = "3"
                        binding.searchTv.hint = "Dimension C-137, unknown..."}
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.searchTv.hint = "Выберите параметр"
            }

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