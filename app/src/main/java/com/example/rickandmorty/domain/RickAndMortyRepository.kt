package com.example.rickandmorty.domain

import android.net.Uri
import com.example.rickandmorty.models.*
import retrofit2.Response
import retrofit2.http.Url

interface RickAndMortyRepository {
    //getList
    suspend fun getCharactersList(page: Int): Response<CharactersInfo>
    suspend fun getLocationsList(page: Int): Response<LocationsInfo>
    suspend fun getEpisodesList(page: Int): Response<EpisodesInfo>
    suspend fun getCharByUrl(url: Uri): Response<CharactersInfo>
    suspend fun getEpByUrl(url: Uri): Response<EpisodesInfo>
    suspend fun getLocByUrl(url: Uri): Response<LocationsInfo>

    //searchBy
    suspend fun searchCharByName(name: String): Response<CharactersInfo>
    suspend fun searchCharByStatus(status: String): Response<CharactersInfo>
    suspend fun searchCharBySpecies(species: String): Response<CharactersInfo>
    suspend fun searchCharByType(type: String): Response<CharactersInfo>
    suspend fun searchCharByGender(gender: String): Response<CharactersInfo>
    suspend fun searchLocByName(name: String): Response<LocationsInfo>
    suspend fun searchLocByType(type:String): Response<LocationsInfo>
    suspend fun searchLocByDimension(dimension: String): Response<LocationsInfo>
    suspend fun searchEpByName(name: String): Response<EpisodesInfo>
    suspend fun searchEpByEp(episode: String): Response<EpisodesInfo>

}