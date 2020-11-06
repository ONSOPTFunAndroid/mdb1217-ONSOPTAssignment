package com.example.myapplication

import android.content.Intent
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sample_item_list.view.*

class SampleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val title: TextView = itemView.findViewById(R.id.item_title);
    private val subTitle: TextView = itemView.findViewById(R.id.item_subTitle);

    fun onBind(data : ProfileData) {
        title.text = data.item_title
        subTitle.text = data.item_subTitle
    }

    override fun onClick(v: View?) {
        val intent = Intent(v?.context, Detailed_Activity::class.java)
        v?.context?.startActivity(intent)
    }


}