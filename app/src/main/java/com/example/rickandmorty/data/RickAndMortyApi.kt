package com.example.rickandmorty.data

import android.net.Uri
import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.EpisodesInfo
import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response
import retrofit2.http.GET
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
    suspend fun getCharacterByUrl(
        @Url() url: Uri
    ): Response<CharactersInfo>

    @GET("api/character/")
    suspend fun searchCharByName(
        @Query("name") name: String
    ): Response<CharactersInfo>

    @GET("api/character/")
    suspend fun searchCharByStatus(
        @Query("status") status: String
    ): Response<CharactersInfo>

    @GET("api/character/")
    suspend fun searchCharBySpecies(
        @Query("species") species: String
    ): Response<CharactersInfo>

    @GET("api/character/")
    suspend fun searchCharByGender(
        @Query("gender") gender: String
    ): Response<CharactersInfo>

    @GET("api/location")
    suspend fun searchLocByName(
        @Query("name") name: String
    ): Response<LocationsInfo>

    @GET("api/location")
    suspend fun searchLocByType(
        @Query("type") type: String
    ): Response<LocationsInfo>

    @GET("api/location")
    suspend fun searchLocByDimension(
        @Query("dimension") dimension: String
    ): Response<LocationsInfo>

    @GET()
    suspend fun getLocByUrl(
        @Url() url: Uri
    ): Response<LocationsInfo>

    @GET("api/episode")
    suspend fun searchEpByName(
        @Query("name") name: String
    ): Response<EpisodesInfo>

    @GET("api/episode")
    suspend fun searchEpByEpisode(
        @Query("episode") episode: String
    ): Response<EpisodesInfo>

    @GET()
    suspend fun getEpByUrl(
        @Url() url: Uri
    ): Response<EpisodesInfo>
}