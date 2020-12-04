package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val url: TextView = itemView.findViewById(R.id.text_url);
    private val title: TextView = itemView.findViewById(R.id.text_title);
    private val contents: TextView = itemView.findViewById(R.id.text_detail)

    fun onBind(data: WebSearchData.Document) {
        url.text = data.url
        title.text = data.title
        contents.text = data.contents
    }

}