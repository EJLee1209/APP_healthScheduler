package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.databinding.ActivityListBinding
import com.google.android.material.tabs.TabLayout

class ExersizeList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction2 = supportFragmentManager.beginTransaction()
        transaction2.replace(R.id.rv_exercises, BackFragment()).commit() // 디폴트 프래그먼트 설정
        binding.tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{ //탭 클릭 이벤트
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when(tab?.text){
                    "등" -> transaction.replace(R.id.rv_exercises, BackFragment())
                    "가슴" -> transaction.replace(R.id.rv_exercises, ChestFragment())
                    "하체" -> transaction.replace(R.id.rv_exercises, LowerFragment())
                }
                transaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })


    }
}