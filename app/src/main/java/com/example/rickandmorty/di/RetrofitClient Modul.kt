package com.example.rickandmorty.di

import com.example.rickandmorty.data.RickAndMortyApi
import com.example.rickandmorty.data.RickAndMortyRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    fun create(gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://rickandmortyapi.com/")
        .build()


    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): RickAndMortyApi =
        retrofit.create(RickAndMortyApi::class.java)

    @Singleton
    @Provides
    fun provideRepo(service: RickAndMortyApi) =RickAndMortyRepositoryImpl(service)
}




