package com.example.rickandmorty.presentation.fragments.episodes

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.*
import com.example.rickandmorty.models.Episodes
import com.example.rickandmorty.models.EpisodesInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(private val repository: RickAndMortyRepository):ViewModel() {

    val episodes = MutableLiveData<EpisodesInfo>()
    var parameter = MutableLiveData<String>()

    private val getEpisodesListUseCase = GetEpisodesListUseCase(repository)
    private val searchEpisodeByName = SearchEpByNameUseCase(repository)
    private val searchEpByEp = SearchEpByEpUseCase(repository)
    private val getEpByUrl = GetEpByUrlUseCase(repository)

    init {
        getEpisodes(1)
    }

    fun getEpisodes(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {kotlin.runCatching {
            val call = getEpisodesListUseCase.getEpisodes(page)
            if (call.isSuccessful)episodes.postValue(call.body())
            }
        }
    }

    fun searchEpByName(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchEpisodeByName.searchEpByName(name)
                if (call.isSuccessful)episodes.postValue(call.body())
            }
        }
    }

    fun searchEpByEp(episode: String){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = searchEpByEp.searchEpByEp(episode)
                if (call.isSuccessful)episodes.postValue(call.body())
            }
        }
    }

    fun getEpByUrl(url: Uri){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getEpByUrl.getEpByUrl(url)
                if (call.isSuccessful)episodes.postValue(call.body())
            }
        }
    }

    fun spinner(spinner: Spinner, searchTv: EditText){
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0 -> {parameter.postValue("0")
                        searchTv.hint = "Выберите параметр"
                    }
                    1 -> {parameter.postValue("1")
                        searchTv.hint = "Имя"
                    }
                    2 -> {parameter.postValue("2")
                        searchTv.hint = "S03E07, S01E07, ..." }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                searchTv.hint = "Выберите параметр"
            }

        }
    }

    fun setupSpinnerAdapter(spinner: Spinner, context: Context) {
        context.let {
            ArrayAdapter.createFromResource(
                it.applicationContext,
                R.array.parameterEP,
                R.layout.customtxt
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.dropdown_spinner)
                spinner.adapter = adapter
            }
        }
    }
}