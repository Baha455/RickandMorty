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
import com.example.rickandmorty.presentation.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class CharactersFragment:Fragment() {

    private lateinit var binding: CharactersFragmentBinding
    private val charAdapter by lazy { SearchAdapter() }
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
    }

    private fun initRv() {
        viewModel.characters.observe(viewLifecycleOwner, {
            charAdapter.appendList(it.results)
        })
        binding.charRv.adapter = charAdapter
    }

    private fun getCharacters() {
        binding.btnSearch.setOnClickListener{
            val etText = binding.searchTv.text.toString()
            viewModel.parameterFilter.observe(viewLifecycleOwner,{
                parameter = it.toString()
            })
            when(parameter){
                "0" -> Toast.makeText(context, "Выберите параметр", Toast.LENGTH_SHORT).show()
                "1" -> viewModel.searchByStatus("Alive")
                "2" -> viewModel.searchByStatus("Dead")
                "3" -> viewModel.searchBySpecies("Alien")
                "4" -> viewModel.searchByGender("Female")
                "5" -> viewModel.searchBySpecies("Human")
                "6" -> viewModel.searchByName(etText)
            }
        }

        binding.btnFilter.setOnClickListener{
            context?.let { it1 -> viewModel.showDialog(it1, layoutInflater, binding.searchTv) }

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

}




