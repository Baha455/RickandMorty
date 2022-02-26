package com.example.rickandmorty.domain

import com.example.rickandmorty.models.EpisodesInfo
import retrofit2.Response

class SearchEpByNameUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchEpByName(name: String): Response<EpisodesInfo>{
        return repository.searchEpByName(name)
    }
}