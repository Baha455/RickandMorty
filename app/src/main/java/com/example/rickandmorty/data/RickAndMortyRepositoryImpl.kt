package com.example.rickandmorty.data

import android.app.Service
import com.example.rickandmorty.domain.RickAndMortyRepository
import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.EpisodesInfo
import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response
import java.security.PrivilegedAction

class RickAndMortyRepositoryImpl(private val service: RickAndMortyApi): RickAndMortyRepository {

    override suspend fun getCharactersList(): Response<CharactersInfo> {
        return service.getCharactersList()
    }

    override suspend fun getLocationsList(): Response<LocationsInfo> {
        return service.getLocationsList()
    }

    override suspend fun getEpisodesList(): Response<EpisodesInfo> {
        return service.getEpisodesList()
    }
}