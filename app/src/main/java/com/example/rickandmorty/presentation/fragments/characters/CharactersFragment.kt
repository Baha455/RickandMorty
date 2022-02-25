package com.example.rickandmorty.presentation.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.CharactersFragmentBinding
import com.example.rickandmorty.models.Characters
import com.example.rickandmorty.presentation.adapter.CharAdapter
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinApplication.Companion.init

class CharactersFragment:Fragment() {

    private lateinit var binding: CharactersFragmentBinding
    private val adapter by lazy { CharAdapter() }
    private val viewModel by viewModel<CharactersViewModel>()
    var page = 1

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
    }

    private fun initRv() {
        viewModel.characters.observe(viewLifecycleOwner, {
            adapter.appendList(it)
        })
        binding.charRv.adapter = adapter
    }

    private fun getCharacters() {
        val url = "https://rickandmortyapi.com/api/character/2".toUri()
        binding.btnNext.setOnClickListener {
            if (page == 42) {
                Toast.makeText(context, "Вы на последней странице", Toast.LENGTH_SHORT).show()
            }else{
                searchCharByName()
            }
        }
        binding.btnPrev.setOnClickListener {
            if (page <= 1) {
                Toast.makeText(context, "Вы на первое странице", Toast.LENGTH_SHORT).show()
            }else{
                prevPage()
            }
        }

    }

    private fun searchCharByName(){
        val name = "Morty"
        viewModel.searchByName(name)
    }

    private fun nextPage(){
        page++
        viewModel.getCharacters(page)
        Toast.makeText(context, "Новые страницы загружены", Toast.LENGTH_SHORT).show()
    }

    private fun prevPage(){
        page--
        viewModel.getCharacters(page)
        Toast.makeText(context, "Предыдущие страницы загружены", Toast.LENGTH_SHORT).show()
    }

    private fun initListener(){
        adapter.onShopItemClickListener ={
            toCharDetailFragment(it)
        }
    }

    private fun toCharDetailFragment(char: Characters) {
        val destination = CharactersFragmentDirections.actionCharactersFragmentToCharDetailFragment(char)
        findNavController().navigate(destination)
    }

}




