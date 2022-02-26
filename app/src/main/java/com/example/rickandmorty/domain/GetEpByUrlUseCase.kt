package com.example.rickandmorty.domain

import android.net.Uri
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.EpisodesInfo
import retrofit2.Response

class GetEpByUrlUseCase(private val repository: RickAndMortyRepository){

    suspend fun getEpByUrl(url: Uri): Response<EpisodesInfo>{
        return repository.getEpByUrl(url)
    }
}