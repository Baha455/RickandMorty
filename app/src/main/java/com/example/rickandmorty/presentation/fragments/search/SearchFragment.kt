package com.example.rickandmorty.presentation.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.LocationFragmentBinding
import com.example.rickandmorty.databinding.SearchFragmentBinding

class SearchFragment: Fragment() {

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SearchFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}