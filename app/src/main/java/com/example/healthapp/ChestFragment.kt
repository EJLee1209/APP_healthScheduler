package com.example.healthapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.databinding.FragmentChestBinding


class ChestFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentChestBinding.inflate(layoutInflater)

        val exerciseList = arrayListOf<Exercises>( //가슴운동 리스트
            Exercises(R.drawable.barbell, "덤벨 체스트 플라이"),
            Exercises(R.drawable.barbell, "덤벨 풀오버(가슴)"),
            Exercises(R.drawable.barbell, "덤벨프레스"),
            Exercises(R.drawable.barbell, "디클라인 덤벨프레스"),
            Exercises(R.drawable.barbell, "디클라인 벤치프레스"),
            Exercises(R.drawable.barbell, "디클라인 체스트프레스"),
            Exercises(R.drawable.barbell, "푸시업"),
            Exercises(R.drawable.barbell, "딥스"),
            Exercises(R.drawable.barbell, "벤치프레스"),
            Exercises(R.drawable.barbell, "인클라인 벤치프레스"),
            Exercises(R.drawable.barbell, "스미스머신 벤치프레스"),
            Exercises(R.drawable.barbell, "인클라인 덤벨프레스"),
        )
        binding.rvChest.layoutManager = LinearLayoutManager(activity)
        binding.rvChest.setHasFixedSize(true)
        binding.rvChest.adapter = ExerciseAdapter(exerciseList)
        return binding.root
    }
}