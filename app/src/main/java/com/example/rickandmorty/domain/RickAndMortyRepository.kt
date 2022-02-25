package com.example.rickandmorty.domain

import android.net.Uri
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.models.CharactersInfo
import com.example.rickandmorty.models.EpisodesInfo
import com.example.rickandmorty.models.LocationsInfo
import retrofit2.Response
import retrofit2.http.Url

interface RickAndMortyRepository {
    suspend fun getCharactersList(page: Int): Response<CharactersInfo>
    suspend fun getLocationsList(page: Int): Response<LocationsInfo>
    suspend fun getEpisodesList(page: Int): Response<EpisodesInfo>
    suspend fun getCharacter(url: Uri): Response<Characters>
    suspend fun searchCharByName(name: String): Response<CharactersInfo>
}