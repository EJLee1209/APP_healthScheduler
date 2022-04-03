package com.example.healthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.databinding.ActivityListBinding
import com.google.android.material.tabs.TabLayout

class ExersizeList1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val exerciseList = arrayListOf<Exercises>( //등운동 리스트
            Exercises(R.drawable.barbell, "랫풀다운"),
            Exercises(R.drawable.barbell, "풀업"),
            Exercises(R.drawable.barbell, "덤벨로우"),
            Exercises(R.drawable.barbell, "원암 덤벨로우"),
            Exercises(R.drawable.barbell, "티바로우"),
            Exercises(R.drawable.barbell, "암풀다운"),
            Exercises(R.drawable.barbell, "덤벨 풀오버"),
            Exercises(R.drawable.barbell, "루마니안 데드리프트"),
            Exercises(R.drawable.barbell, "바벨로우"),
            Exercises(R.drawable.barbell, "백 익스텐션"),
            Exercises(R.drawable.barbell, "시티드 로우"),
            Exercises(R.drawable.barbell, "원 암 케이블 로우"),
        )

        binding.rvExercises.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvExercises.setHasFixedSize(true)
        binding.rvExercises.adapter = ExerciseAdapter(exerciseList)


    }
}