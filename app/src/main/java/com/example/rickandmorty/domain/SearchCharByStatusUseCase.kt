package com.example.rickandmorty.domain

import com.example.rickandmorty.models.CharactersInfo
import retrofit2.Response

class SearchCharByStatusUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchCharByStatus(status: String): Response<CharactersInfo>{
        return repository.searchCharByStatus(status)
    }
}