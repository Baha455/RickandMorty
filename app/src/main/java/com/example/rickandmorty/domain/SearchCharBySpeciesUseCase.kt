package com.example.rickandmorty.domain

import com.example.rickandmorty.models.CharactersInfo
import retrofit2.Response

class SearchCharBySpeciesUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchCharBySpecies(species: String): Response<CharactersInfo>{
        return repository.searchCharBySpecies(species)
    }
}