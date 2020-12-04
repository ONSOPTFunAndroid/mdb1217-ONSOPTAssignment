package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        login_btn.setOnClickListener{
            val email = editTextTextPersonName.text.toString()
            val password = editTextTextPersonName2.text.toString()
            val call : Call<ResponseLoginData> = SoptServiceImpl.service.postLogin(
                RequestLoginData(email = email, password = password)
            )
            call.enqueue(object : Callback<ResponseLoginData> {
                override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {

                }
                override fun onResponse(
                    call: Call<ResponseLoginData>,
                    response: Response<ResponseLoginData>
                ) {
                    response.takeIf { it.isSuccessful}
                        ?.body()
                        ?.let { it ->
                            Toast.makeText(this@MainActivity, it.data.userName + "님 반갑습니다.", Toast.LENGTH_SHORT).show()
                            //it.data.userName
                            saveData()
                            val intent = Intent(this@MainActivity, Activity::class.java)
                            startActivity(intent)
                        } ?: showError(response.errorBody())
                }
            })
            //Toast.makeText(this, "반갑습니다. " + editTextTextPersonName.text + "님", Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 1001)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == 1002) {
            if (data != null) {
                editTextTextPersonName.setText(data.extras?.getString("id"))
            }
            if (data != null) {
                editTextTextPersonName2.setText(data.extras?.getString("pass"))
            }
        }
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()
        if(editTextTextPersonName.text.toString().isNotEmpty() && editTextTextPersonName2.text.toString().isNotEmpty()) {
            edit.putString("id", editTextTextPersonName.text.toString())
            edit.putString("pass", editTextTextPersonName2.text.toString())
            edit.putBoolean("check", true)
            edit.apply()
        }
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        if(pref.getBoolean("check", false)) {
            Toast.makeText(this, "자동로그인 완료", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
        }
    }

    private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"),Toast.LENGTH_SHORT).show()
    }
}