package com.example.rickandmorty.di

import com.example.rickandmorty.domain.GetCharactersListUseCase
import com.example.rickandmorty.domain.GetEpisodesListUseCase
import com.example.rickandmorty.domain.GetLocationsListUseCase
import org.koin.dsl.module
import kotlin.math.sin

val useCaseModule = module {

    single { GetCharactersListUseCase(get()) }
    single { GetLocationsListUseCase(get()) }
    single { GetEpisodesListUseCase(get()) }

}

