package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detailed_.*
import kotlinx.android.synthetic.main.activity_main.*

class Detailed_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_)

        textView7.text = intent.getStringExtra("title")
        textView8.text = intent.getStringExtra("subtitle")
        if(textView7.text == "이름") {
            textView9.text = "2020-10-28"
            textView11.text = "우리 부모님께서 지어주신 아름다운 이름이다. 조금의 TMI를 얘기하자면 한글이름이다."
        }
        else if(textView7.text == "나이") {
            textView9.text = "2020-10-28"
            textView11.text = "암 퉤니쓰리 난 수수께끼! 아직은 젊은 나이 23살! 하고싶은 게 너무 많다!"
        }
        else if(textView7.text == "파트") {
            textView9.text = "2020-10-29"
            textView11.text = "밍팟장님이 있는 솝트 체고의 파트! 안드로이드 파트 사랑합니다. 실습하면서 많은 걸 배워가요..!"
        }
        else if(textView7.text == "GitHub") {
            textView9.text = "2020-10-30"
            textView11.text = "나의 여러가지 잡다한 코드들이 있는 깃허브 주소이다. 관심있는 사람은 한 번쯤 방문해보는 것도 좋지않을까?"
        }
        else if(textView7.text == "Blog") {
            textView9.text = "2020-10-30"
            textView11.text = "블로그 서이추 해주세요..ㅠㅠ 저 블로그 열심히 합니다.. 정말로.. 아마도..?"
        }
        else if(textView7.text == "Sopt") {
            textView9.text = "2020-10-30"
            textView11.text = "명실상부 대한민국 최고의 동아리입니다. 당장 가입해주세요 지금 당장 라잇나우.. 저는 솝트에 뼈를 묻겠습니다..!"
        }
    }
}