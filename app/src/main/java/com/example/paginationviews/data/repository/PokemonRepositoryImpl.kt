package com.example.paginationviews.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.log
import androidx.paging.map
import com.example.paginationviews.data.api.ApiService
import com.example.paginationviews.data.model.Pokemon
import com.example.paginationviews.domain.repository.PokemonRepository
import com.example.paginationviews.data.paging.PokemonPagingSource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PokemonRepository {


    override fun getPokemonStream(): Flow<PagingData<Pokemon>> {
        Log.d("PokemonRepositoryImpl", "getPokemonStream() called")
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                Log.d("PokemonRepositoryImpl", "Creating PagingSource")
                PokemonPagingSource(apiService)
            }
        ).flow
            .onEach { pagingData ->
                Log.d("PokemonRepositoryImpl", "PagingData emitted")

            }
    }
}