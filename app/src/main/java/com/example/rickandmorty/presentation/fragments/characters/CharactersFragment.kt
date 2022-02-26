package com.example.rickandmorty.presentation.fragments.characters

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharactersFragmentBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.presentation.adapter.CharAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment:Fragment() {

    private lateinit var binding: CharactersFragmentBinding
    private val adapter by lazy { CharAdapter() }
    private val viewModel by viewModels<CharactersViewModel>()
    var page = 1
    lateinit var parameter: String
    var url: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharactersFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCharacters()
        initRv()
        initListener()
        setupSpinnerAdapter()
        spinner()
    }

    private fun initRv() {
        viewModel.characters.observe(viewLifecycleOwner, {
            adapter.appendList(it.results)
        })
        binding.charRv.adapter = adapter
    }

    private fun getCharacters() {
        binding.btnNext.setOnClickListener {
            if (page == 42) {
                Toast.makeText(context, "Вы на последней странице", Toast.LENGTH_SHORT).show()
            }else{
                page++
                nextPage()
            }
        }
        binding.btnPrev.setOnClickListener {
            if (page <= 1) {

                Toast.makeText(context, "Вы на первое странице", Toast.LENGTH_SHORT).show()
            }else{
                page--
                prevPage()
            }
        }
        binding.btnSearch.setOnClickListener{
            val etText = binding.searchTv.text.toString()
            when(parameter){
                "0" -> Toast.makeText(context, "Выберите параметр", Toast.LENGTH_SHORT).show()
                "1" -> viewModel.searchByName(etText)
                "2" -> viewModel.searchByStatus(etText)
                "3" -> viewModel.searchBySpecies(etText)
                "4" -> viewModel.searchByType(etText)
                "5" -> viewModel.searchByGender(etText)
            }
        }
    }

    private fun nextPage(){
        viewModel.characters.observe(viewLifecycleOwner, {
            url = it.info.next.toUri()
        })
        url?.let { viewModel.getCharByUrl(it) }
        Toast.makeText(context, "Новые страницы загружены", Toast.LENGTH_SHORT).show()
    }

    private fun prevPage(){
        viewModel.characters.observe(viewLifecycleOwner,{
            if (it.info.prev!= null) {
                url = it.info.prev.toUri()
            }else{
                Toast.makeText(context, "Вы на первой странице", Toast.LENGTH_SHORT).show()
            }
        })
        if(url!= null) {
            viewModel.getCharByUrl(url!!)
        }
    }

    private fun initListener(){
        adapter.onShopItemClickListener ={
            toCharDetailFragment(it)
        }
    }

    private fun setupSpinnerAdapter() {
        context?.let {
            ArrayAdapter.createFromResource(
                it.applicationContext,
                R.array.parameterChar,
                R.layout.customtxt
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.dropdown_spinner)
                binding.spinner.adapter = adapter
            }
        }
    }

    private fun spinner(){
        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               when(p2){
                   0 -> {parameter = "0"
                       binding.searchTv.hint = "Выберите параметр"}
                   1 -> {parameter = "1"
                       binding.searchTv.hint = "Имя"
                   }
                   2 -> {parameter = "2"
                        binding.searchTv.hint = "Alive, dead, unknown"}
                   3 -> {parameter = "3"
                        binding.searchTv.hint = "Human, alien, unknown..."}
                   4 -> {parameter = "4"
                        binding.searchTv.hint = "Genetic experiment"}
                   5 -> {parameter = "5"
                        binding.searchTv.hint = "Female, male, genderless or unknown"}
               }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.searchTv.hint = "Выберите параметр"
            }

        }
    }

    private fun toCharDetailFragment(char: Characters) {
        val destination = CharactersFragmentDirections.actionCharactersFragmentToCharDetailFragment(char)
        findNavController().navigate(destination)
    }

}




