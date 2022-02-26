package com.example.rickandmorty.presentation.fragments.episodes

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.*
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.EpisodesInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodesViewModel(private val repository: RickAndMortyRepository):ViewModel() {

    val episodes = MutableLiveData<EpisodesInfo>()

    private val getEpisodesListUseCase = GetEpisodesListUseCase(repository)
    private val searchEpisodeByName = SearchEpByNameUseCase(repository)
    private val searchEpByEp = SearchEpByEpUseCase(repository)
    private val getEpByUrl = GetEpByUrlUseCase(repository)

    init {
        getEpisodes(1)
    }

    fun getEpisodes(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {kotlin.runCatching {
            val call = getEpisodesListUseCase.getEpisodes(page)
            if (call.isSuccessful)episodes.postValue(call.body())
            }
        }
    }

    fun searchEpByName(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchEpisodeByName.searchEpByName(name)
                if (call.isSuccessful)episodes.postValue(call.body())
            }
        }
    }

    fun searchEpByEp(episode: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchEpByEp.searchEpByEp(episode)
                if (call.isSuccessful)episodes.postValue(call.body())
            }
        }
    }

    fun getEpByUrl(url: Uri){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getEpByUrl.getEpByUrl(url)
                if (call.isSuccessful)episodes.postValue(call.body())
            }
        }
    }
}