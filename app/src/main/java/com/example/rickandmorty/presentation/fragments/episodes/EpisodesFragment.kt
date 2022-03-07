package com.example.rickandmorty.presentation.fragments.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.databinding.EpisodesFragmentBinding
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.presentation.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment: Fragment() {

    private lateinit var binding: EpisodesFragmentBinding
    private val viewModel by viewModels<EpisodesViewModel>()
    private val adapter by lazy { SearchAdapter() }
    lateinit var parameter: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EpisodesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        getEpisodes()
        initListener()
    }

    private fun initRv() {
        viewModel.episodes.observe(viewLifecycleOwner, {
            adapter.appendList(it.results)

        })
        binding.episodesRv.adapter = adapter
    }

    private fun getEpisodes() {
        binding.btnSearch.setOnClickListener{
            val etText = binding.searchTv.text.toString()
            viewModel.parameterFilter.observe(viewLifecycleOwner, {
                parameter = it.toString()
            })
            when(parameter){
                "0" -> Toast.makeText(context, "Выберите параметр", Toast.LENGTH_SHORT).show()
                "1" -> viewModel.searchEpByName(etText)
                "2" -> viewModel.searchEpByEp(etText)

            }
        }

        binding.btnFilter.setOnClickListener{
            context?.let { it1 -> viewModel.showDialog(it1, layoutInflater, binding.searchTv) }

        }

        binding.btnNext.setOnClickListener{
            viewModel.getEpByUrl("https://rickandmortyapi.com/api/episode".toUri())
        }
    }

    private fun initListener(){
        adapter.onItemClickListener = {
            toEpisodesDetailFragment(it as Episodes)
        }
    }

    private fun toEpisodesDetailFragment(episodes: Episodes){
        val destination = EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeDetailFragment(episodes)
        findNavController().navigate(destination)
    }

}