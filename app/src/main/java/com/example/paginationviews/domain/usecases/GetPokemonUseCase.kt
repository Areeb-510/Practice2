package com.example.paginationviews.domain.usecases

import androidx.paging.PagingData
import com.example.paginationviews.data.model.Pokemon
import com.example.paginationviews.data.repository.PokemonRepositoryImpl
import com.example.paginationviews.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    fun execute(): Flow<PagingData<Pokemon>> {

        return repository.getPokemonStream()
    }
}