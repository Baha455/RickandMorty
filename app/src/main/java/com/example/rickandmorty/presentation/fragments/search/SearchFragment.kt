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
import com.example.rickandmorty.presentation.adapter.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private lateinit var binding: SearchFragmentBinding
    private val viewModel by viewModels<SearchFragmentViewModel>()
    private val adapterChar by lazy { CharAdapter2() }
    private val adapterEp by lazy { CharAdapter2() }
    private val adapterLoc by lazy { CharAdapter2() }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
        viewModel.episodes.observe(viewLifecycleOwner,{
            adapterEp.appendList(it.results)
        })
        viewModel.chars.observe(viewLifecycleOwner, {
            adapterChar.appendList(it.results)
        })
        viewModel.locations.observe(viewLifecycleOwner,{
            adapterLoc.appendList(it.results)
        })
        binding.charRv.adapter = adapterChar
        binding.episodesRv.adapter = adapterEp
        binding.locationsRv.adapter = adapterLoc
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
            toCharDetailFragment(it as Characters)
        }
        adapterLoc.onItemClickListener ={
            toLocDetailFragment(it as Locations)
        }
        adapterEp.onItemClickListener ={
            toEpDetailFragment(it as Episodes)
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