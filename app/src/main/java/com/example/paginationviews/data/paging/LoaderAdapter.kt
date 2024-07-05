package com.example.paginationviews.data.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationviews.R

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoadViewHolder>() {
    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        holder.progressBar.visibility = if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loader_item, parent, false)
        return LoadViewHolder(view)
    }


    inner class LoadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
    }

}