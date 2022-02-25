package com.example.rickandmorty.domain

import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response

class GetLocationsListUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getLocationsList(page: Int): Response<LocationsInfo> {
        return repository.getLocationsList(page)
    }
}