package com.example.rickandmorty.presentation.fragments.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.databinding.EpisodesFragmentBinding
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.presentation.adapter.EpisodeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodesFragment: Fragment() {

    private lateinit var binding: EpisodesFragmentBinding
    private val viewModel by viewModel<EpisodesViewModel>()
    private val adapter by lazy { EpisodeAdapter() }
    var page = 1

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
        viewModel.getEpisodes(page)
        initRv()
        getEpisodes()
        initListener()
    }

    private fun initRv() {
        viewModel.episodes.observe(viewLifecycleOwner, {
            adapter.appendList(it)
        })
        binding.episodesRv.adapter = adapter
    }

    private fun getEpisodes(){
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
        viewModel.getEpisodes(page)
        Toast.makeText(context, "Новые страницы загружены", Toast.LENGTH_SHORT).show()
    }

    private fun prevPage(){
        page--
        viewModel.getEpisodes(page)
        Toast.makeText(context, "Предыдущие страницы загружены", Toast.LENGTH_SHORT).show()
    }

    private fun initListener(){
        adapter.onShopItemClickListener = {
            toEpisodesDetailFragment(it)
        }
    }

    private fun toEpisodesDetailFragment(episodes: Episodes){
        val destination = EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeDetailFragment(episodes)
        findNavController().navigate(destination)
    }
}