package com.example.paginationviews.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.paginationviews.data.model.Pokemon
import com.example.paginationviews.domain.usecases.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    val pokemonStream: Flow<PagingData<Pokemon>> = getPokemonUseCase.execute()
        .cachedIn(viewModelScope)
        .onEach { Log.d("PokemonViewModel", "Received PagingData in ViewModel") }

    init {
        viewModelScope.launch {
            Log.d("PokemonViewModel", "Launching coroutine to collect paging data")
            pokemonStream.collectLatest { pagingData ->
                Log.d("PokemonViewModel", "Collecting PagingData in ViewModel")
            }
        }
    }
}