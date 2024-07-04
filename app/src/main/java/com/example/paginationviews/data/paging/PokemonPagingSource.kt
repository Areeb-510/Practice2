package com.example.paginationviews.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationviews.data.api.ApiService
import com.example.paginationviews.data.model.Pokemon

class PokemonPagingSource(private val apiService: ApiService) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val position = params.key ?: 0

        return try {
            val response = apiService.getPokemons(offset = position, limit = params.loadSize)
            Log.d("TAG", "load:${response.results}")
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (response.next == null) null else position + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(state.config.pageSize) ?: anchorPage?.nextKey?.minus(state.config.pageSize)
        }
    }
}