package com.example.rickandmorty.domain

import com.example.rickandmorty.models.CharactersInfo
import retrofit2.Response

class GetCharactersListUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getCharactersList(): Response<CharactersInfo>{
        return repository.getCharactersList()
    }
}