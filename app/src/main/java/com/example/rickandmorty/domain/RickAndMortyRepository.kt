package com.example.rickandmorty.domain

import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.EpisodesInfo
import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response

interface RickAndMortyRepository {
    suspend fun getCharactersList(): Response<CharactersInfo>
    suspend fun getLocationsList(): Response<LocationsInfo>
    suspend fun getEpisodesList(): Response<EpisodesInfo>
}