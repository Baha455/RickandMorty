package com.example.rickandmorty.domain

import com.example.rickandmorty.models.CharactersInfo
import retrofit2.Response

class SearchCharByNameUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchCharByName(name: String): Response<CharactersInfo>{
        return repository.searchCharByName(name)
    }
}