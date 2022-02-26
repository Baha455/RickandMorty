package com.example.rickandmorty.domain

import android.net.Uri
import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response

class GetLocByUrlUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getLocByUrl(url: Uri): Response<LocationsInfo>{
        return repository.getLocByUrl(url)
    }
}