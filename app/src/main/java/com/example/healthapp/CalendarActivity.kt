package com.example.healthapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.healthapp.databinding.ActivityCalendarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.FileInputStream
import java.io.FileOutputStream

class CalendarActivity : AppCompatActivity() {
    private var mBinding : ActivityCalendarBinding? = null
    private val binding get() = mBinding!!
    private var auth : FirebaseAuth? = null
    private var initTime = 0L

    var userId: String = "userId"
    lateinit var fname : String
    lateinit var str : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.calendarView.setOnDateChangeListener { view, year, month, day ->
            binding.diaryTextView.text = "${year}/${month+1}/${day}"
            binding.savedText.visibility = View.INVISIBLE // 저장된 텍스트 숨기기
            binding.contextEditText.visibility = View.VISIBLE // 입력창 보이기
            binding.saveTextButton.visibility = View.VISIBLE //저장버튼 보이기
            binding.editTextButton.visibility = View.INVISIBLE //수정버튼 숨기기
            binding.deleteTextButton.visibility = View.INVISIBLE //삭제버튼 숨기기
            checkDay(year, month, day, userId)
        }

        binding.saveTextButton.setOnClickListener{
            if(binding.contextEditText.text.toString() == "") {
                Toast.makeText(this,"내용을 입력하시고 저장을 눌러주세요",Toast.LENGTH_SHORT).show()
            }else{
                binding.contextEditText.visibility = View.INVISIBLE
                binding.deleteTextButton.visibility = View.VISIBLE
                binding.editTextButton.visibility = View.VISIBLE
                binding.saveTextButton.visibility = View.INVISIBLE
                binding.savedText.visibility = View.VISIBLE
                binding.savedText.text = binding.contextEditText.text.toString()
                saveData(fname)
                binding.contextEditText.setText("")
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
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)

    }
    @SuppressLint("WrongConstant")
    fun saveData(fname: String) {
        var fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(fname, MODE_NO_LOCALIZED_COLLATORS)
            val content = binding.savedText.text.toString()
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
    // 달력 내용 제거
    @SuppressLint("WrongConstant")
    fun removeDiary(readDay: String?) {
        var fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            val content = ""
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    private fun checkDay(year: Int, month: Int, day: Int, userId: String) {
        fname = ""+userId + year + "-" + month + "-" + day + ".txt" //저장할 파일 이름

        var fileInputStream: FileInputStream
        try{
            fileInputStream = openFileInput(fname)
            val fileData = ByteArray(fileInputStream.available())
            fileInputStream.read(fileData)
            fileInputStream.close()
            str = String(fileData)
            binding.savedText.visibility = View.VISIBLE
            binding.savedText.setText(str)


            binding.deleteTextButton.setOnClickListener{ //삭제 버튼 클릭
                binding.savedText.visibility = View.INVISIBLE // 저장된 텍스트 숨기기
                binding.savedText.setText("") //저장된 텍스트 ""로 변경
                binding.editTextButton.visibility = View.INVISIBLE //수정버튼 숨기기
                binding.deleteTextButton.visibility = View.INVISIBLE //삭제버튼 숨기기
                binding.saveTextButton.visibility = View.VISIBLE //저장버튼 보이기
                binding.contextEditText.visibility = View.VISIBLE
                removeDiary(fname)
            }
            binding.editTextButton.setOnClickListener{ //수정 버튼 클릭
                binding.editTextButton.visibility = View.INVISIBLE
                binding.deleteTextButton.visibility = View.INVISIBLE
                binding.contextEditText.visibility = View.VISIBLE
                binding.saveTextButton.visibility = View.VISIBLE
                str = binding.savedText.text.toString()
                binding.contextEditText.setText(str)

            }
            if(binding.savedText.text.toString() != ""){
                binding.saveTextButton.visibility = View.INVISIBLE
                binding.deleteTextButton.visibility = View.VISIBLE
                binding.editTextButton.visibility = View.VISIBLE
                binding.contextEditText.visibility = View.INVISIBLE
            }

        } catch (e: Exception){
            e.printStackTrace()
        }

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
