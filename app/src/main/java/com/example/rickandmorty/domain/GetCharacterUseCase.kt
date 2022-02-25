package com.example.rickandmorty.domain

import android.net.Uri
import com.example.rickandmorty.models.Characters
import retrofit2.Response
import retrofit2.http.Url

class GetCharacterUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getCharacter(url: Uri): Response<Characters>{
        return repository.getCharacter(url)
    }
}