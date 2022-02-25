package com.example.rickandmorty.domain

import com.example.rickandmorty.models.CharactersInfo
import retrofit2.Response

class GetCharactersListUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getCharactersList(page: Int): Response<CharactersInfo>{
        return repository.getCharactersList(page)
    }
}