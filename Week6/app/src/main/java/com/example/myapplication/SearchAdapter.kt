package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter(private val context: Context) : RecyclerView.Adapter<SearchViewHolder>() {
    var data = mutableListOf<WebSearchData.Document>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.search_list, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(data[position])
    }


    override fun getItemCount(): Int = data.size

}