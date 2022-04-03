package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.healthapp.databinding.ActivityCalendarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CalendarActivity : AppCompatActivity() {
    private var mBinding : ActivityCalendarBinding? = null
    private val binding get() = mBinding!!
    private var auth : FirebaseAuth? = null
    private var initTime = 0L
    private var isClicked = false

    var userId: String = "userId"
    lateinit var fname : String
    lateinit var str : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.calendarView.setOnDateChangeListener { view, year, month, day -> //캘린더 클릭 이벤트
            binding.selectDate.text = "${year}-${month}-${day}"
            isClicked = true

        }
        binding.exerciseFrame.setOnClickListener{
            if(isClicked) {
                val intent = Intent(this, ExersizeList1::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "날짜를 선택해주세요",Toast.LENGTH_SHORT).show()
            }
        }



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //옵션메뉴 생성
        val menuItem1: MenuItem? = menu?.add(0,0,0,"로그아웃") //로그아웃을 위한 메뉴생성
        val menuItem2: MenuItem? = menu?.add(0,1,0,"운동목록") //로그아웃을 위한 메뉴생성
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){ //옵션메뉴 클릭이벤트 처리
        0->{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)//메인액티비티화면으로 이동
            auth?.signOut() //로그아웃
            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            true
        }
        1->{
            val intent = Intent(this, ExersizeList1::class.java)
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)

    }




    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){ //뒤로가기 키 눌렀을 때
            if(System.currentTimeMillis() - initTime > 3000){ //뒤로가기 버튼을 처음 눌렀거나 3초가 지났을 때 처리
                initTime = System.currentTimeMillis()
                Toast.makeText(this, "종료하시려면 한번 더 눌러주세요",Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
