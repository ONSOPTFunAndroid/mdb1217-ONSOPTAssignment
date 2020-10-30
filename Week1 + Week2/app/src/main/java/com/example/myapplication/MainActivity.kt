package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        login_btn.setOnClickListener{
            Toast.makeText(this, "반갑습니다. " + editTextTextPersonName.text + "님", Toast.LENGTH_SHORT).show()
            saveData()
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
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
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

}