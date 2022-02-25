package com.example.rickandmorty.presentation.fragments.locations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.GetLocationsListUseCase
import com.example.rickandmorty.domain.RickAndMortyRepository
import com.example.rickandmorty.models.Locations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationsViewModel(private val repository: RickAndMortyRepository):ViewModel() {

    val locations = MutableLiveData<List<Locations>>()

    private val getLocationsListUseCase = GetLocationsListUseCase(repository)

    init {
        getLocations(1)
    }

    fun getLocations(page: Int){
        viewModelScope.launch(Dispatchers.IO) {kotlin.runCatching {
            val call = getLocationsListUseCase.getLocationsList(page)
            if (call.isSuccessful)locations.postValue(call.body()?.results)
        }  }
    }
}