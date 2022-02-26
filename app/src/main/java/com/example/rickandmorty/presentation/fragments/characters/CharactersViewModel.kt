package com.example.rickandmorty.presentation.fragments.characters

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.*
import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.Info
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class CharactersViewModel(private val repository: RickAndMortyRepository) :ViewModel() {

    val characters = MutableLiveData<CharactersInfo>()
    val pages = MutableLiveData<Info>()

    init {
        getCharacters(1)
    }
    private val getCharactersList = GetCharactersListUseCase(repository)
    private val getCharByUrl = GetCharacterByUrlUseCase(repository)
    private val searchCharByName = SearchCharByNameUseCase(repository)
    private val searchCharByStatus = SearchCharByStatusUseCase(repository)
    private val searchCharBySpecies = SearchCharBySpeciesUseCase(repository)
    private val searchCharByType = SearchCharByTypeUseCase(repository)
    private val searchCharByGender = SearchCharByGenderUseCase(repository)

    fun getCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharactersList.getCharactersList(page)
                if (call.isSuccessful)characters.postValue(call.body())
            }
        }
    }

    fun getCharByUrl(url: Uri){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharByUrl.getCharacter(url)
                if (call.isSuccessful)characters.postValue(call.body())
                }
            }
        }

    fun searchByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharByName.searchCharByName(name)
                if (call.isSuccessful) characters.postValue(call.body())
                }
            }
        }

    fun searchByStatus(status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharByStatus.searchCharByStatus(status)
                if (call.isSuccessful) characters.postValue(call.body())
                }
            }
        }

    fun searchBySpecies(species: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharBySpecies.searchCharBySpecies(species)
                if (call.isSuccessful)characters.postValue(call.body())
            }
        }
    }

    fun searchByType(type: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharByType.searchCharByType(type)
                if (call.isSuccessful)characters.postValue(call.body())
            }
        }
    }

    fun searchByGender(gender: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharByGender.searchCharByGender(gender)
                if (call.isSuccessful)characters.postValue(call.body())
            }
        }
    }
}
