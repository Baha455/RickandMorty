package com.example.rickandmorty.presentation.fragments.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.EpisodesFragmentBinding
import com.example.rickandmorty.presentation.adapter.EpisodeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodesFragment: Fragment() {

    private lateinit var binding: EpisodesFragmentBinding
    private val viewModel by viewModel<EpisodesViewModel>()
    private val adapter by lazy { EpisodeAdapter(){

    } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = EpisodesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEpisodes()
        initVM()
        initRv()
    }

    private fun initVM(){
        viewModel.episodes.observe(viewLifecycleOwner,{
            adapter.updateData(it)
        })
    }
    private fun initRv(){
        binding.episodesRv.adapter = adapter
    }
}