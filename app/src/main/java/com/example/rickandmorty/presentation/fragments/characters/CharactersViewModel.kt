package com.example.rickandmorty.presentation.fragments.characters

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
import com.example.rickandmorty.models.CharactersInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: RickAndMortyRepository) :ViewModel() {

    val characters = MutableLiveData<CharactersInfo>()
    var parameterFilter = MutableLiveData<String>()

    init {
        getCharacters(1)
    }
    private val getCharactersList = GetCharactersListUseCase(repository)
    private val getCharByUrl = GetCharacterByUrlUseCase(repository)
    private val searchCharByName = SearchCharByNameUseCase(repository)
    private val searchCharByStatus = SearchCharByStatusUseCase(repository)
    private val searchCharBySpecies = SearchCharBySpeciesUseCase(repository)
    private val searchCharByGender = SearchCharByGenderUseCase(repository)

    private fun getCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharactersList.getCharactersList(page)
                if (call.isSuccessful)characters.postValue(call.body())
            }
        }
    }

    fun getCharByUrl(url: Uri){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharByUrl.getCharacter(url)
                if (call.isSuccessful)characters.postValue(call.body())
                }
            }
        }

    fun searchByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharByName.searchCharByName(name)
                if (call.isSuccessful) characters.postValue(call.body())
                }
            }
        }

    fun searchByStatus(status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharByStatus.searchCharByStatus(status)
                if (call.isSuccessful) characters.postValue(call.body())
                }
            }
        }

    fun searchBySpecies(species: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharBySpecies.searchCharBySpecies(species)
                if (call.isSuccessful)characters.postValue(call.body())
            }
        }
    }

    fun searchByGender(gender: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchCharByGender.searchCharByGender(gender)
                if (call.isSuccessful)characters.postValue(call.body())
            }
        }
    }

    fun showDialog(context: Context, layoutInflater: LayoutInflater, searchTv:EditText) {
        val dialog = context.let { Dialog(it) }
        val dialogBinding = FilterDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        context.let {
            ArrayAdapter.createFromResource(
                it.applicationContext,
                R.array.filterChar,
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
                    0 -> {parameterFilter.postValue("0")
                    searchTv.hint = "Выберите параметр"}

                    1 -> { parameterFilter.postValue("1")
                    searchTv.hint = "Alive"}

                    2 -> {parameterFilter.postValue("2")
                    searchTv.hint = "Dead"}

                    3 -> {parameterFilter.postValue("3")
                    searchTv.hint = "Alien"}

                    4 -> {parameterFilter.postValue("4")
                    searchTv.hint = "Female"}

                    5 -> {parameterFilter.postValue("5")
                    searchTv.hint = "Human"}

                    6 -> {parameterFilter.postValue("6")
                    searchTv.hint = "Введите имя"}
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            }
        dialogBinding.btnChose.setOnClickListener(){
            dialog.hide()
        }
    }
}
