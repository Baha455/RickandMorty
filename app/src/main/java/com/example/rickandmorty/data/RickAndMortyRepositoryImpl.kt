package com.example.rickandmorty.data

import android.net.Uri
import com.example.rickandmorty.domain.RickAndMortyRepository
import com.example.rickandmorty.models.*
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

    override suspend fun getCharByUrl(url: Uri): Response<CharactersInfo> {
        return service.getCharacterByUrl(url)
    }

    override suspend fun searchCharByName(name: String): Response<CharactersInfo> {
        return service.searchCharByName(name)
    }

    override suspend fun searchCharByStatus(status: String): Response<CharactersInfo> {
        return service.searchCharByStatus(status)
    }

    override suspend fun searchCharBySpecies(species: String): Response<CharactersInfo> {
        return service.searchCharBySpecies(species)
    }

    override suspend fun searchCharByType(type: String): Response<CharactersInfo> {
        return service.searchCharByType(type)
    }

    override suspend fun searchCharByGender(gender: String): Response<CharactersInfo> {
        return service.searchCharByGender(gender)
    }

    override suspend fun searchLocByName(name: String): Response<LocationsInfo> {
        return service.searchLocByName(name)
    }

    override suspend fun searchLocByType(type: String): Response<LocationsInfo> {
        return service.searchLocByType(type)
    }

    override suspend fun searchLocByDimension(dimension: String): Response<LocationsInfo> {
        return service.searchLocByDimension(dimension)
    }

    override suspend fun searchEpByName(name: String): Response<EpisodesInfo> {
        return service.searchEpByName(name)
    }

    override suspend fun searchEpByEp(episode: String): Response<EpisodesInfo> {
        return service.searchEpByEpisode(episode)
    }

    override suspend fun getLocByUrl(url: Uri): Response<LocationsInfo> {
        return service.getLocByUrl(url)
    }

    override suspend fun getEpByUrl(url: Uri): Response<EpisodesInfo> {
        return service.getEpByUrl(url)
    }
}