package com.example.groupactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

class RaceAdapterClass(
    private val raceList: List<RaceDataClass>,
    private val onEditClickListener: OnEditClickListener
) : RecyclerView.Adapter<RaceAdapterClass.RaceViewHolder>() {

    interface OnEditClickListener {
        fun onEditClick(position: Int, raceData: RaceDataClass)
        fun onDeleteClick(position: Int, raceData: RaceDataClass)
    }

    class RaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val raceName: TextView = itemView.findViewById(R.id.raceNameTitle)
        val raceDate: TextView = itemView.findViewById(R.id.raceDateTitle)
        val editButton: Button = itemView.findViewById(R.id.edtRaceResult_Btn_EDT)
        val deleteButton: Button = itemView.findViewById(R.id.edtRaceResult_Btn_DEL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.findrace_resultcard_layout, parent, false)
        return RaceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RaceViewHolder, position: Int) {
        val currentRace = raceList[position]

        holder.raceName.text = currentRace.raceId
        holder.raceDate.text = currentRace.date

        // Set up the "Edit" button functionality
        holder.editButton.setOnClickListener {
            onEditClickListener.onEditClick(position, currentRace)
        }

        // Set up the "Delete" button functionality
        holder.deleteButton.setOnClickListener {
            onEditClickListener.onDeleteClick(position, currentRace)
        }
    }

    override fun getItemCount(): Int {
        return raceList.size
    }
}
