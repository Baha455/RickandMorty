package com.example.rickandmorty.presentation.fragments.episodes

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
import com.example.rickandmorty.databinding.EpisodesFragmentBinding
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.presentation.adapter.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment: Fragment() {

    private lateinit var binding: EpisodesFragmentBinding
    private val viewModel by viewModels<EpisodesViewModel>()
    private val adapter by lazy { EpisodeAdapter() }
    var page = 1
    lateinit var parameter: String
    var url: Uri? = null
    var pages: Int? = null

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
        context?.let { viewModel.setupSpinnerAdapter(binding.spinner, it) }
        viewModel.spinner(binding.spinner, binding.searchTv)
    }

    private fun initRv() {
        viewModel.episodes.observe(viewLifecycleOwner, {
            adapter.appendList(it.results)
        })
        binding.episodesRv.adapter = adapter
    }

    private fun getEpisodes() {
        viewModel.episodes.observe(viewLifecycleOwner,{
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
                "1" -> viewModel.searchEpByName(etText)
                "2" -> viewModel.searchEpByEp(etText)

            }
        }
    }

    private fun nextPage(){
        viewModel.episodes.observe(viewLifecycleOwner, {
            if (it.info.next != null) {
                url = it.info.next.toUri()
            } else {
                Toast.makeText(context, "Вы на последней странице", Toast.LENGTH_SHORT).show()
            }
        })
        url?.let { viewModel.getEpByUrl(it) }
        Toast.makeText(context, "Новые страницы загружены", Toast.LENGTH_SHORT).show()
    }

    private fun prevPage(){
        viewModel.episodes.observe(viewLifecycleOwner,{
            if (it.info.prev!= null) {
                url = it.info.prev.toUri()
            }else{
                Toast.makeText(context, "Вы на первой странице", Toast.LENGTH_SHORT).show()
            }
        })
        if(url!= null) {
            viewModel.getEpByUrl(url!!)
        }
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