package com.example.rickandmorty.domain

import com.example.rickandmorty.models.CharactersInfo
import retrofit2.Response

class SearchCharByGenderUseCase(private val repository: RickAndMortyRepository) {
    suspend fun searchCharByGender(gender: String): Response<CharactersInfo>{
        return repository.searchCharByGender(gender)
    }
}