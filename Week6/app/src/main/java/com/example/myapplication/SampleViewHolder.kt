package com.example.myapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SampleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val first: TextView = itemView.findViewById(R.id.item_first_name);
    private val last: TextView = itemView.findViewById(R.id.item_last_name);
    private val email: TextView = itemView.findViewById(R.id.item_email)
    private val profile: ImageView = itemView.findViewById(R.id.imageView)

    fun onBind(data: ResponseUserData.Data) {
        Glide.with(itemView)
            .load(data.avatar)
            .placeholder(R.drawable.nunsong)
            .error(R.drawable.nunsong)
            .into(profile)

        first.text = data.first_name
        last.text = data.last_name
        email.text = data.email
    }

    /*override fun onClick(v: View?) {
        val intent = Intent(v?.context, Detailed_Activity::class.java)
        v?.context?.startActivity(intent)
    }*/

}