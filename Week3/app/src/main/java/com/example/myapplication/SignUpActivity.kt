package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        button.setOnClickListener{
            var name : String = editTextTextPersonName3.text.toString()
            var id : String = editTextTextPersonName4.text.toString()
            var pass : String = editTextTextPersonName5.text.toString()

            if(name.isNotEmpty() && id.isNotEmpty() && pass.isNotEmpty()) {
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
            }
        }
    }
}