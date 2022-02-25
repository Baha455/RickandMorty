package com.example.rickandmorty.data

import android.net.Uri
import com.example.rickandmorty.domain.RickAndMortyRepository
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.EpisodesInfo
import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response
import retrofit2.http.Url

class RickAndMortyRepositoryImpl(private val service: RickAndMortyApi): RickAndMortyRepository {

    override suspend fun getCharactersList(page: Int): Response<CharactersInfo> {
        return service.getCharactersList(page)
    }

    override suspend fun getLocationsList(page: Int): Response<LocationsInfo> {
        return service.getLocationsList(page)
    }

    override suspend fun getEpisodesList(page: Int): Response<EpisodesInfo> {
        return service.getEpisodesList(page)
    }

    override suspend fun getCharacter(url: Uri): Response<Characters> {
        return service.getCharacter(url)
    }

    override suspend fun searchCharByName(name: String): Response<CharactersInfo> {
        return service.searchCharByName(name)
    }
}