package com.example.rickandmorty.presentation.fragments.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.RickAndMortyRepository
import com.example.rickandmorty.models.Characters

class CharDetailFragmentViewModel(private val repository: RickAndMortyRepository):ViewModel() {

    val charDetail = MutableLiveData<Characters>()
}