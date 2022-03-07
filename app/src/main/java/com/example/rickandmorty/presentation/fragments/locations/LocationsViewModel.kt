package com.example.rickandmorty.presentation.fragments.locations

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FilterDialogBinding
import com.example.rickandmorty.domain.*
import com.example.rickandmorty.models.Locations
import com.example.rickandmorty.models.LocationsInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val repository: RickAndMortyRepository):ViewModel() {

    val locations = MutableLiveData<LocationsInfo>()
    var parameter = MutableLiveData<String>()
    var parameterFilter = MutableLiveData<String>()

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

    fun showDialog(context: Context, layoutInflater: LayoutInflater, searchTv: EditText) {
        val dialog = context.let { Dialog(it) }
        val dialogBinding = FilterDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        context.let {
            ArrayAdapter.createFromResource(
                it.applicationContext,
                R.array.filterLoc,
                R.layout.customtxt
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.dropdown_spinner)
                dialogBinding.filterSpinner.adapter = adapter
            }
        }
        dialog.show()

        dialogBinding.filterSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0 -> {parameter.postValue("0")
                        searchTv.hint = "Выберите параметр"}
                    1 -> {parameterFilter.postValue("1")
                        searchTv.hint = "Название"}

                    2 -> {parameterFilter.postValue("2")
                        searchTv.hint = "Тип: Space station, Resort, Planet"}

                    3 -> {parameterFilter.postValue("3")
                        searchTv.hint = "Измерение: Dimension C-137, unknown"}

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                parameterFilter.postValue("")
            }
        }
        dialogBinding.btnChose.setOnClickListener(){
            dialog.hide()
        }
    }
}