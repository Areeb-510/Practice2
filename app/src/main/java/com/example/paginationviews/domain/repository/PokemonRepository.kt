package com.example.paginationviews.domain.repository

import androidx.paging.PagingData
import com.example.paginationviews.data.model.Pokemon
import com.example.paginationviews.data.model.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository{

    fun getPokemonStream(): Flow<PagingData<Pokemon>>

}