package com.example.rickandmorty.domain

import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response

class SearchLocByNameUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchLocByName(name: String): Response<LocationsInfo>{
        return repository.searchLocByName(name)
    }
}