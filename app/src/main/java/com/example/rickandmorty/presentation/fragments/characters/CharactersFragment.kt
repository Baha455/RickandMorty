package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.databinding.CharactersFragmentBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.presentation.adapter.CharAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class CharactersFragment:Fragment() {

    private lateinit var binding: CharactersFragmentBinding
    private val charAdapter by lazy { CharAdapter() }
    val viewModel by viewModels<CharactersViewModel>()
    private lateinit var parameter: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharactersFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCharacters()
        initRv()
        initListener()
        viewModel.spinner(binding.spinner, binding.searchTv)
        context?.let { viewModel.setupSpinnerAdapter(binding.spinner, it) }
    }

    private fun initRv() {
        viewModel.characters.observe(viewLifecycleOwner, {
            charAdapter.modifyList(it.results)
        })
        binding.charRv.adapter = charAdapter
    }

    private fun getCharacters() {
        binding.btnSearch.setOnClickListener{
            val etText = binding.searchTv.text.toString()
            viewModel.parameterSearch.observe(viewLifecycleOwner,{
                parameter = it.toString()
            })
            when(parameter){
                "0" -> Toast.makeText(context, "Выберите параметр", Toast.LENGTH_SHORT).show()
                "1" -> viewModel.searchByName(etText)
                "2" -> viewModel.searchByStatus(etText)
                "3" -> viewModel.searchBySpecies(etText)
                "4" -> viewModel.searchByType(etText)
                "5" -> viewModel.searchByGender(etText)
            }
        }

        binding.btnFilter.setOnClickListener{
            context?.let { it1 -> viewModel.showDialog(it1, layoutInflater) }
            filterList()
        }

        binding.btnNext.setOnClickListener{
            viewModel.getCharByUrl("https://rickandmortyapi.com/api/character".toUri())
        }
    }

    private fun initListener(){
        charAdapter.onItemClickListener ={
            toCharDetailFragment(it as Characters)
        }
    }

    private fun toCharDetailFragment(char: Characters) {
        val destination = CharactersFragmentDirections.actionCharactersFragmentToCharDetailFragment(char)
        findNavController().navigate(destination)
    }

    private fun filterList(){
        viewModel.parameterFilter.observe(viewLifecycleOwner,{
            charAdapter.filter(it)
        })
    }
}




