package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val call : Call<ResponseUserData> = UserListImpl.service.GetUser()
        call.enqueue(object : Callback<ResponseUserData> {
            override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ResponseUserData>,
                response: Response<ResponseUserData>
            ) {
                response.takeIf { it.isSuccessful}
                    ?.body()
                    ?.let { it ->
                        //it.data.userName
                        sampleAdapter.data = it.data as MutableList<ResponseUserData.Data>
                        sampleAdapter.notifyDataSetChanged()
                    } ?: showError(response.errorBody())
            }
        })

        button7.setOnClickListener {
            removeData()
            this.activity?.finish()
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

    private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(activity, ob.getString("message"),Toast.LENGTH_SHORT).show()
    }
}