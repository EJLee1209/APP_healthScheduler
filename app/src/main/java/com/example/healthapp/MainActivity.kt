package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.example.healthapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private var initTime = 0L
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //뷰바인딩
        auth = Firebase.auth

        setContentView(binding.root)

        binding.loginButton.setOnClickListener{ //로그인 버튼 클릭 이벤트 처리
            signIn(binding.userId.text.toString(), binding.userPassword.text.toString())
        }
        binding.registerButton.setOnClickListener{ //회원가입 버튼
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    public override fun onStart(){ //로그아웃하지 않았을 시 자동 로그인, 회원가입시 바로 로그인 됨
        super.onStart()
        moveMainPage(auth?.currentUser)
    }
    private fun signIn(email: String, password: String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    moveMainPage(auth?.currentUser)
                }else{
                    Toast.makeText(this, "로그인 실패 아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else{
            Toast.makeText(this, "이메일과 비밀번호 모두 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun moveMainPage(user: FirebaseUser?) {
        if(user != null){
            startActivity(Intent(this, CalendarActivity::class.java))
            finish()
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