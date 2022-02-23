package com.example.rickandmorty.di

import com.example.rickandmorty.data.RickAndMortyRepositoryImpl
import com.example.rickandmorty.domain.RickAndMortyRepository
import org.koin.dsl.module

val repoModules = module {
    single { RickAndMortyRepositoryImpl(get())}
    single { provideRemoteRepository(get()) }
}


private fun provideRemoteRepository(repoImpl: RickAndMortyRepositoryImpl): RickAndMortyRepository {
    return repoImpl
}