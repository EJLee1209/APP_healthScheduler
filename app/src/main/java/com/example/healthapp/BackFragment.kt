package com.example.healthapp

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.databinding.FragmentBackBinding

class BackFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentBackBinding.inflate(layoutInflater)
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
        binding.rvBack.layoutManager = LinearLayoutManager(activity)
        binding.rvBack.setHasFixedSize(true)
        binding.rvBack.adapter = ExerciseAdapter(exerciseList)

        return binding.root
    }
}