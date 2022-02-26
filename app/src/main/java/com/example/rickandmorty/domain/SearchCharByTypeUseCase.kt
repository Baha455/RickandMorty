package com.example.rickandmorty.domain

import com.example.rickandmorty.models.CharactersInfo
import retrofit2.Response

class SearchCharByTypeUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchCharByType(type: String): Response<CharactersInfo>{
        return repository.searchCharByType(type)
    }
}