package com.example.rickandmorty.presentation.fragments.characters

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharactersFragmentBinding
import com.example.rickandmorty.databinding.FilterDialogBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.presentation.adapter.CharAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class CharactersFragment:Fragment() {

    private lateinit var binding: CharactersFragmentBinding
    private val charAdapter by lazy { CharAdapter() }
    val viewModel by viewModels<CharactersViewModel>()
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
        viewModel.spinner(binding.spinner, binding.searchTv)
        context?.let { viewModel.setupSpinnerAdapter(binding.spinner, it) }
        btn()
    }

    private fun initRv() {
        viewModel.characters.observe(viewLifecycleOwner, {
            charAdapter.appendList(it.results)
        })
        binding.charRv.adapter = charAdapter
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
            viewModel.parameter.observe(viewLifecycleOwner,{
                parameter = it.toString()
            })
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
            viewModel.getCharByUrl(url!!)

    }

    private fun initListener(){
        charAdapter.onItemClickListener ={
            toCharDetailFragment(it)
        }
    }

    private fun toCharDetailFragment(char: Characters) {
        val destination = CharactersFragmentDirections.actionCharactersFragmentToCharDetailFragment(char)
        findNavController().navigate(destination)
    }

    private fun btn(){
        binding.btnFilter.setOnClickListener{
            filterList()
        }
    }

    private fun showDialog(){
        val dialog = context?.let { Dialog(it) }
        val dialogBinding = FilterDialogBinding.inflate(layoutInflater)
        dialog?.setContentView(dialogBinding.root)
        context?.let {
            ArrayAdapter.createFromResource(
                it.applicationContext,
                R.array.filterChar,
                R.layout.customtxt
            ).also {adapter ->
                adapter.setDropDownViewResource(R.layout.dropdown_spinner)
                dialogBinding.filterSpinner.adapter = adapter
            }
        }
        dialog?.show()
    }
    private fun filterList(){
        val etetx = binding.searchTv.text.toString()
        charAdapter.filter(etetx)
    }
}




