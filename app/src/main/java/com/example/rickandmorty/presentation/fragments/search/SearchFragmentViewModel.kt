package com.example.rickandmorty.presentation.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.RickAndMortyRepository
import com.example.rickandmorty.domain.SearchCharByNameUseCase
import com.example.rickandmorty.domain.SearchEpByNameUseCase
import com.example.rickandmorty.domain.SearchLocByNameUseCase
import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.EpisodesInfo
import com.example.rickandmorty.models.LocationsInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(private val repository: RickAndMortyRepository):ViewModel() {

    val chars = MutableLiveData<CharactersInfo>()
    val locations = MutableLiveData<LocationsInfo>()
    val episodes = MutableLiveData<EpisodesInfo>()

    private val searchCharByName = SearchCharByNameUseCase(repository)
    private val searchLocByName = SearchLocByNameUseCase(repository)
    private val searchEpByName = SearchEpByNameUseCase(repository)

    fun getCharsByName(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharByName.searchCharByName(name)
                if (call.isSuccessful)chars.postValue(call.body())
            }
        }
    }

    fun getLocByName(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchLocByName.searchLocByName(name)
                if (call.isSuccessful)locations.postValue(call.body())
            }
        }
    }

    fun getEpByName(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchEpByName.searchEpByName(name)
                if (call.isSuccessful)episodes.postValue(call.body())
            }
        }
    }

}