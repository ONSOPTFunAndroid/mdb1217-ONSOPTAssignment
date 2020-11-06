package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sampleAdapter = activity?.let { SampleAdapter(it) }!!

        sampleAdapter.itemClick = object : SampleAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val title: TextView = view.findViewById(R.id.item_title);
                val subTitle: TextView = view.findViewById(R.id.item_subTitle);
                val intent = Intent(view.context, Detailed_Activity::class.java)
                intent.putExtra("title", title.text.toString())
                intent.putExtra("subtitle", subTitle.text.toString())
                startActivity(intent)
            }
        }

        val itemTouchHelperCallback = ItemTouchHelperCallback(sampleAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(main_rcv)

        sampleAdapter.setItemViewType(0);
        main_rcv.adapter = sampleAdapter
        main_rcv.layoutManager = LinearLayoutManager(activity)

        switch2.setOnCheckedChangeListener { CompoundButton, onSwitch ->
            //  스위치가 켜지면
            if (onSwitch) {
                sampleAdapter.setItemViewType(1)
                main_rcv.adapter = sampleAdapter
                main_rcv.layoutManager = GridLayoutManager(activity, 2)
            }
            //  스위치가 꺼지면
            else {
                sampleAdapter.setItemViewType(0);
                main_rcv.adapter = sampleAdapter
                main_rcv.layoutManager = LinearLayoutManager(activity)
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
            Intent()
        }
    }

    private fun removeData() {
        val pref = this.activity?.getSharedPreferences("pref", 0)
        val edit = pref?.edit()
        edit?.remove("id")
        edit?.remove("pass")
        edit?.remove("check")

        edit?.apply()

        Toast.makeText(activity, "로그아웃 완료", Toast.LENGTH_SHORT).show()
    }
}