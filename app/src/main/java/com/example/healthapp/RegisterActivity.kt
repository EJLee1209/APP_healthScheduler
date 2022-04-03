//회원가입 액티비티
package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.example.healthapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? = null //전역 변수로 FirebaseAuth객체를 선언
    private var mBinding : ActivityRegisterBinding? = null
    private val binding get() = mBinding!!
    private var databaseRef : DatabaseReference? = null //실시간 데이터 베이스

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth //oncreate안에서 할당
        databaseRef = FirebaseDatabase.getInstance().getReference("healthApp")

        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerButton2.setOnClickListener{ //회원가입버튼 클릭 이벤트 처리
            if(binding.newPassword.text.toString() == binding.newPassword2.text.toString()) //비밀번호 확인
                createAccount(binding.newId.text.toString(), binding.newPassword.text.toString())
            else //비밀번호가 일치하지 않으면
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
        }


    }

    private fun createAccount(email: String, password: String){ //계정생성
        if(email.isNotEmpty() && password.isNotEmpty()){ //아이디 비밀번호가 모두 입력되었으면
            auth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){ //정상적으로 계정이 생성되면
                    var firebaseUser : FirebaseUser? = auth?.currentUser
                    var account :UserAccount = UserAccount()
                    if (firebaseUser != null) {
                        account.setIdToken(firebaseUser.uid)
                    }
                    account.setEmailId(firebaseUser?.email)
                    account.setPassword(binding.newPassword.text.toString())

                    if (firebaseUser != null) {
                        databaseRef?.child("UserAccount")?.child(firebaseUser.uid)?.setValue(account)
                    }

                    Toast.makeText(this, "계정 생성 완료", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "계정 생성 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }else{ //빈 항목이 있는 경우
            Toast.makeText(this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){ //뒤로가기 키 눌렀을 때
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}