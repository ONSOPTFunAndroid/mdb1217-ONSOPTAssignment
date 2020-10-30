package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SampleAdapter (private val context : Context) : RecyclerView.Adapter<SampleViewHolder>(), ItemTouchHelperListener {
    var data = mutableListOf<ProfileData>()
    var changeViewType = 0

    interface ItemClick
    {
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.sample_item_list, parent, false)
        if(viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item_list, parent, false)
        }
        return SampleViewHolder(view)
    }

    fun setItemViewType(sw : Int) {
        changeViewType = sw
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) : Int{
        return changeViewType
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(data[position])
        if(itemClick != null)
        {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = data.removeAt(from)
        data.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}