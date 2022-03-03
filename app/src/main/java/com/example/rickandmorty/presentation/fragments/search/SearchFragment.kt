package com.example.rickandmorty.presentation.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.databinding.SearchFragmentBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.Locations
import com.example.rickandmorty.presentation.adapter.CharAdapter
import com.example.rickandmorty.presentation.adapter.EpisodeAdapter
import com.example.rickandmorty.presentation.adapter.LocationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private lateinit var binding: SearchFragmentBinding
    private val viewModel by viewModels<SearchFragmentViewModel>()
    private val adapterChar by lazy { CharAdapter() }
    private val adapterLoc by lazy { LocationsAdapter()}
    private val adapterEp by lazy { EpisodeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SearchFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        getData()
        setupListeners()
    }

    private fun initRv(){
        viewModel.chars.observe(viewLifecycleOwner,{
            adapterChar.appendList(it.results as MutableList<Characters>)
        })
        binding.charRv.adapter = adapterChar
        viewModel.locations.observe(viewLifecycleOwner,{
            adapterLoc.appendList(it.results)
        })
        binding.locationsRv.adapter = adapterLoc
        viewModel.episodes.observe(viewLifecycleOwner,{
            adapterEp.appendList(it.results)
        })
        binding.episodesRv.adapter = adapterEp
    }

    private fun getData(){
        binding.btnStart.setOnClickListener{
            val etText = binding.searchTv.text.toString()
            viewModel.getCharsByName(etText)
            viewModel.getEpByName(etText)
            viewModel.getLocByName(etText)
        }
    }

    private fun setupListeners(){
        adapterChar.onItemClickListener ={
            toCharDetailFragment(it)
        }
        adapterLoc.onShopItemClickListener ={
            toLocDetailFragment(it)
        }
        adapterEp.onShopItemClickListener ={
            toEpDetailFragment(it)
        }
    }

    private fun toCharDetailFragment(char: Characters){
        val destination = SearchFragmentDirections.actionSearchFragmentToCharDetailFragment(char)
        findNavController().navigate(destination)
    }

    private fun toLocDetailFragment(loc: Locations){
        val destination = SearchFragmentDirections.actionSearchFragmentToLocationDetailFragment(loc)
        findNavController().navigate(destination)
    }

    private fun toEpDetailFragment(ep: Episodes){
        val destination = SearchFragmentDirections.actionSearchFragmentToEpisodeDetailFragment(ep)
        findNavController().navigate(destination)
    }
}