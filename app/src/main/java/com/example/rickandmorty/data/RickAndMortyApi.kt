package com.example.rickandmorty.data

import android.net.Uri
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.EpisodesInfo
import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyApi {

    @GET("api/character/")
    suspend fun getCharactersList(
        @Query("page") page: Int
    ): Response<CharactersInfo>

    @GET("api/location")
    suspend fun getLocationsList(
        @Query("page") page: Int
    ): Response<LocationsInfo>

    @GET("api/episode")
    suspend fun getEpisodesList(
        @Query("page") page: Int
    ): Response<EpisodesInfo>

    @GET()
    suspend fun getCharacter(
        @Url() url: Uri
    ): Response<Characters>

    @GET("api/character/")
    suspend fun searchCharByName(
        @Query("name") name: String
    ): Response<CharactersInfo>
}