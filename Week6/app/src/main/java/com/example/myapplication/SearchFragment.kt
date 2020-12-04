package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_blank.*
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
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    private lateinit var searchAdapter: SearchAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter = activity?.let { SearchAdapter(it) }!!

        search_rcv.adapter = searchAdapter
        search_rcv.layoutManager = LinearLayoutManager(activity)

        search_button.setOnClickListener {
            val call: Call<WebSearchData> = SearchServiceImpl.service.getWebSearch(search_text.text.toString())

            call.enqueue(object : Callback<WebSearchData> {
                override fun onFailure(call: Call<WebSearchData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<WebSearchData>,
                    response: Response<WebSearchData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let { it ->
                            //it.data.userName
                            searchAdapter.data = it.documents as MutableList<WebSearchData.Document>
                            searchAdapter.notifyDataSetChanged()
                        } ?: showError(response.errorBody())
                }
            })
        }
    }
    private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(activity, ob.getString("message"), Toast.LENGTH_SHORT).show()
    }
}