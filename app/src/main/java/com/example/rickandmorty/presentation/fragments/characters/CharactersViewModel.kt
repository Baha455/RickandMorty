package com.example.rickandmorty.presentation.fragments.characters

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.GetCharacterUseCase
import com.example.rickandmorty.domain.GetCharactersListUseCase
import com.example.rickandmorty.domain.RickAndMortyRepository
import com.example.rickandmorty.domain.SearchCharByNameUseCase
import com.example.rickandmorty.models.Characters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: RickAndMortyRepository) :ViewModel() {

    val characters =MutableLiveData<List<Characters>>()
    val character =MutableLiveData<Characters>()

    init {
        getCharacters(1)
    }


    private val getCharactersListUseCase = GetCharactersListUseCase(repository)
    private val getCharacterUseCase = GetCharacterUseCase(repository)
    private val searchCharByNameUseCase = SearchCharByNameUseCase(repository)

    fun getCharacters(page: Int){
        viewModelScope.launch(Dispatchers.IO) {kotlin.runCatching {
            val call =getCharactersListUseCase.getCharactersList(page)
            if (call.isSuccessful)characters.postValue(call.body()?.results)
        }  }
    }

    fun getCharacter(url: Uri){
        viewModelScope.launch(Dispatchers.IO) {kotlin.runCatching {
            val call = getCharacterUseCase.getCharacter(url)
            if (call.isSuccessful)character.postValue(call.body())
        }
        }
    }

    fun searchByName(name: String){
        viewModelScope.launch(Dispatchers.IO) {kotlin.runCatching {
            val call = searchCharByNameUseCase.searchCharByName(name)
            if (call.isSuccessful)characters.postValue(call.body()?.results)
        }  }
    }
}