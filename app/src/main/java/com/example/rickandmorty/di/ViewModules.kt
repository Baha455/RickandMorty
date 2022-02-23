package com.example.rickandmorty.di


import com.example.rickandmorty.presentation.fragments.characters.CharactersViewModel
import com.example.rickandmorty.presentation.fragments.episodes.EpisodesViewModel
import com.example.rickandmorty.presentation.fragments.locations.LocationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { CharactersViewModel( get()) }
    viewModel { LocationsViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }

}