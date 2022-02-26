package com.example.rickandmorty.presentation.fragments.locations

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.*
import com.example.rickandmorty.models.Locations
import com.example.rickandmorty.models.LocationsInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationsViewModel(private val repository: RickAndMortyRepository):ViewModel() {

    val locations = MutableLiveData<LocationsInfo>()

    private val getLocationsListUseCase = GetLocationsListUseCase(repository)
    private val searchLocByName = SearchLocByNameUseCase(repository)
    private val searchLocByType = SearchLocByTypeUseCase(repository)
    private val searchLocByDimension = SearchLocByDimensionUseCase(repository)
    private val getLocByUrl = GetLocByUrlUseCase(repository)

    init {
        getLocations(1)
    }

    fun getLocations(page: Int){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getLocationsListUseCase.getLocationsList(page)
                if (call.isSuccessful)locations.postValue(call.body())
        }  }
    }

    fun searchByName(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchLocByName.searchLocByName(name)
                if (call.isSuccessful)locations.postValue((call.body()))
            }
        }
    }

    fun searchByType(type: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchLocByType.searchLocByType(type)
                if (call.isSuccessful)locations.postValue(call.body())
            }
        }
    }

    fun searchByDimension(dimension: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchLocByDimension.searchLocByDimension(dimension)
                if (call.isSuccessful)locations.postValue(call.body())
            }
        }
    }

    fun getByUrl(url: Uri){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getLocByUrl.getLocByUrl(url)
                if (call.isSuccessful)locations.postValue(call.body())
            }
        }
    }
}