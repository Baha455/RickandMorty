package com.example.rickandmorty.presentation.fragments.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.GetCharactersListUseCase
import com.example.rickandmorty.domain.RickAndMortyRepository
import com.example.rickandmorty.models.Characters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CharactersViewModel(private val repository: RickAndMortyRepository) :ViewModel() {

    val characters =MutableLiveData<List<Characters>>()

    private val getCharactersListUseCase = GetCharactersListUseCase(repository)

    fun getCharacters(){
        viewModelScope.launch(Dispatchers.IO) {kotlin.runCatching {
            val call =getCharactersListUseCase.getCharactersList()
            if (call.isSuccessful)characters.postValue(call.body()?.results)
        }  }
    }


}