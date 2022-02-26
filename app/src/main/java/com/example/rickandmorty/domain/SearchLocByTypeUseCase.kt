package com.example.rickandmorty.domain

import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response

class SearchLocByTypeUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchLocByType(type: String): Response<LocationsInfo>{
        return repository.searchLocByType(type)
    }
}