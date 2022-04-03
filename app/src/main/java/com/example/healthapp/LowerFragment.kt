package com.example.healthapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.databinding.FragmentLowerBinding


class LowerFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLowerBinding.inflate(layoutInflater)

        val exerciseList = arrayListOf<Exercises>( //하체 운동 리스트
            Exercises(R.drawable.barbell, "스쿼트"),
            Exercises(R.drawable.barbell, "덤벨 런지"),
            Exercises(R.drawable.barbell, "덤벨 스쿼트"),
            Exercises(R.drawable.barbell, "레그 컬"),
            Exercises(R.drawable.barbell, "레그 익스텐션"),
            Exercises(R.drawable.barbell, "레그 프레스"),
            Exercises(R.drawable.barbell, "로우바 스쿼트"),
            Exercises(R.drawable.barbell, "머신 레그프레스"),
            Exercises(R.drawable.barbell, "스모 데드리프트"),
            Exercises(R.drawable.barbell, "스티프 데드리프트"),
            Exercises(R.drawable.barbell, "카프레이즈"),
            Exercises(R.drawable.barbell, "핵 스쿼트"),
        )
        binding.rvLower.layoutManager = LinearLayoutManager(activity)
        binding.rvLower.setHasFixedSize(true)
        binding.rvLower.adapter = ExerciseAdapter(exerciseList)
        return binding.root
    }
}