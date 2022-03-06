package com.example.rickandmorty.presentation.fragments.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.databinding.EpisodesDetailFragmentBinding

class EpisodeDetailFragment:Fragment(){
    private lateinit var binding: EpisodesDetailFragmentBinding
    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EpisodesDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        val item = args.episodes
        binding.airDateName.text = item.air_date
        binding.episodeName.text = item.name
        binding.episodeNumberName.text = item.episode

    }


}