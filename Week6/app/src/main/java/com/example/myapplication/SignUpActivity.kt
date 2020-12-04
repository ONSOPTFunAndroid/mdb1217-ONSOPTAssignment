package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        button.setOnClickListener{
            val name : String = editTextTextPersonName3.text.toString()
            val email = editTextTextPersonName4.text.toString()
            val password = editTextTextPersonName5.text.toString()
            val call : Call<ResponseSignupData> = SoptSignupImpl.service.postSignup(
                RequestSignupData(email = email, password = password, userName = name)
            )
            call.enqueue(object : Callback<ResponseSignupData> {
                override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<ResponseSignupData>,
                    response: Response<ResponseSignupData>
                ) {
                    response.takeIf { it.isSuccessful}
                        ?.body()
                        ?.let { it ->
                            Toast.makeText(this@SignUpActivity, it.data.email + " 회원가입 완료", Toast.LENGTH_SHORT).show()
                            //it.data.userName
                            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                            setResult(1002, intent)
                            intent.putExtra("name", name)
                            intent.putExtra("id", email)
                            intent.putExtra("pass", password)
                            finish()
                        } ?: showError(response.errorBody())
                }
            })

            /*if(name.isNotEmpty() && id.isNotEmpty() && pass.isNotEmpty()) {
                Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("id", id)
                intent.putExtra("pass", pass)
                setResult(1002, intent)
                finish()
            }
            else {
                Toast.makeText(this, "빈 칸이 있습니다", Toast.LENGTH_SHORT).show()
            }*/
        }
    }

    private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"),Toast.LENGTH_SHORT).show()
    }
}