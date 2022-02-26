package com.example.rickandmorty.domain

import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response

class SearchLocByDimensionUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchLocByDimension(dimension: String): Response<LocationsInfo>{
        return repository.searchLocByDimension(dimension)
    }
}