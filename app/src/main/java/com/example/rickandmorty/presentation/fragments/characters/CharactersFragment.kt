package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.databinding.CharactersFragmentBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.presentation.adapter.CharAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment:Fragment() {

    private lateinit var binding: CharactersFragmentBinding

    private val adapter by lazy { CharAdapter(){toCharDetailFragment(it)} }

    private val viewModel by viewModel<CharactersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CharactersFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacters()
        initVM()
        initRv()

    }

    private fun initVM(){
        viewModel.characters.observe(viewLifecycleOwner, {
            adapter.updateData(it)
        })
    }

    private fun initRv(){
        binding.charRv.adapter = adapter
    }

    private fun toCharDetailFragment(char: Characters){
        val destination = CharactersFragmentDirections.actionCharactersFragmentToCharDetailFragment(char)
        findNavController().navigate(destination)
    }
}