package com.example.rickandmorty.data

import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.EpisodesInfo
import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("api/character")
    suspend fun getCharactersList(): Response<CharactersInfo>

    @GET("api/location")
    suspend fun getLocationsList(): Response<LocationsInfo>

    @GET("api/episode")
    suspend fun getEpisodesList(): Response<EpisodesInfo>
}