package com.example.healthapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(val exerciseList : ArrayList<Exercises>) : RecyclerView.Adapter<ExerciseAdapter.CostomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseAdapter.CostomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CostomViewHolder(view).apply {
            itemView.setOnClickListener {  //리사이클러 뷰 클릭 이벤트
                val curPos : Int = adapterPosition
                val exercise : Exercises = exerciseList.get(curPos)
                Toast.makeText(parent.context, "운동이름 : ${exercise.exerciseName}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBindViewHolder(holder: ExerciseAdapter.CostomViewHolder, position: Int) {
        holder.image.setImageResource(exerciseList.get(position).image)
        holder.exerciseName.text = exerciseList.get(position).exerciseName
    }

    override fun getItemCount(): Int {
        return exerciseList.size

    }
    class CostomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_image)
        val exerciseName = itemView.findViewById<TextView>(R.id.tv_exerciseName)

    }


}