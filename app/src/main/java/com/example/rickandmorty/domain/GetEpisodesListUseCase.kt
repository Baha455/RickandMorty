package com.example.rickandmorty.domain

import com.example.rickandmorty.models.EpisodesInfo
import retrofit2.Response

class GetEpisodesListUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getEpisodes(): Response<EpisodesInfo>{
        return repository.getEpisodesList()
    }
}