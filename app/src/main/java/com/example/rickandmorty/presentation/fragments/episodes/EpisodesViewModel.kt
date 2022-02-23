package com.example.rickandmorty.presentation.fragments.episodes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.GetEpisodesListUseCase
import com.example.rickandmorty.domain.RickAndMortyRepository
import com.example.rickandmorty.models.Episodes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodesViewModel(private val repository: RickAndMortyRepository):ViewModel() {

    val episodes = MutableLiveData<List<Episodes>>()

    val getEpisodesListUseCase = GetEpisodesListUseCase(repository)

    fun getEpisodes(){
        viewModelScope.launch(Dispatchers.IO) {kotlin.runCatching {
            val call = getEpisodesListUseCase.getEpisodes()
            if (call.isSuccessful)episodes.postValue(call.body()?.results)
        }  }
    }
}