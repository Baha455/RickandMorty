package com.example.rickandmorty.di

import com.example.rickandmorty.data.RickAndMortyRepositoryImpl
import com.example.rickandmorty.domain.RickAndMortyRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@dagger.Module
@InstallIn(SingletonComponent::class)
abstract class appModules {

    @Binds
    abstract fun bindRemoteRepository(rickAndMortyRepositoryImpl: RickAndMortyRepositoryImpl
    ): RickAndMortyRepository
}