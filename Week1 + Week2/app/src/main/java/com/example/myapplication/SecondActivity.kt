package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SecondActivity : AppCompatActivity() {
    private lateinit var sampleAdapter: SampleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        sampleAdapter = SampleAdapter(this)

        sampleAdapter.itemClick = object: SampleAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val title: TextView = view.findViewById(R.id.item_title);
                val subTitle: TextView = view.findViewById(R.id.item_subTitle);
                val intent = Intent(view.context, Detailed_Activity::class.java)
                intent.putExtra("title", title.text.toString())
                intent.putExtra("subtitle", subTitle.text.toString())
                setResult(1003, intent)
                startActivity(intent)
            }
        }

        val itemTouchHelperCallback = ItemTouchHelperCallback(sampleAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(main_rcv)

        sampleAdapter.setItemViewType(0);
        main_rcv.adapter = sampleAdapter
        main_rcv.layoutManager = LinearLayoutManager(this)

        switch2.setOnCheckedChangeListener{CompoundButton, onSwitch ->
            //  스위치가 켜지면
            if (onSwitch){
                sampleAdapter.setItemViewType(1)
                main_rcv.adapter = sampleAdapter
                main_rcv.layoutManager = GridLayoutManager(this, 2)
            }
            //  스위치가 꺼지면
            else{
                sampleAdapter.setItemViewType(0);
                main_rcv.adapter = sampleAdapter
                main_rcv.layoutManager = LinearLayoutManager(this)
            }
        }

        sampleAdapter.data = mutableListOf(
            ProfileData("이름", "문다빈"),
            ProfileData("나이", "23"),
            ProfileData("파트", "안드로이드"),
            ProfileData("GitHub", "https://github.com/mdb1217"),
            ProfileData("Blog", "https://blog.naver.com/mdb1217"),
            ProfileData("Sopt", "www.sopt.org")
        )
        sampleAdapter.notifyDataSetChanged()

        button7.setOnClickListener {
            removeData()
            finish()
        }
    }

    private fun removeData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()
        edit.remove("id")
        edit.remove("pass")
        edit.remove("check")

        edit.apply()

        Toast.makeText(this, "로그아웃 완료", Toast.LENGTH_SHORT).show()
    }
}