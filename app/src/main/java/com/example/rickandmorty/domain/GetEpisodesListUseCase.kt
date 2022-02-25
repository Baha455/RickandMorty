package com.example.rickandmorty.domain

import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.EpisodesInfo
import retrofit2.Response

class GetEpisodesListUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getEpisodes(page: Int): Response<EpisodesInfo>{
        return repository.getEpisodesList(page)
    }
}