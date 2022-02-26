package com.example.rickandmorty.domain

import com.example.rickandmorty.models.EpisodesInfo
import retrofit2.Response

class SearchEpByEpUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchEpByEp(episode: String):Response<EpisodesInfo>{
        return repository.searchEpByEp(episode)
    }
}