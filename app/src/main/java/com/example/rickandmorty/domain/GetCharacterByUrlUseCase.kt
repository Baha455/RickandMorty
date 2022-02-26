package com.example.rickandmorty.domain

import android.net.Uri
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.CharactersInfo
import retrofit2.Response
import retrofit2.http.Url

class GetCharacterByUrlUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getCharacter(url: Uri): Response<CharactersInfo>{
        return repository.getCharByUrl(url)
    }
}