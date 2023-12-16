package com.example.groupactivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SeeResultsAdapterClass(
    private val raceList: List<RaceDataClass>,
    private val context: Context
) : RecyclerView.Adapter<SeeResultsAdapterClass.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.seerace_resultcard_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentRace = raceList[position]

        // Bind the data to the ViewHolder views
        holder.raceId.text = currentRace.raceId
        holder.raceDate.text = currentRace.date  // Assuming raceDate is a String in RaceDataClass

        // Set the click listener for the item
        holder.seeResults.setOnClickListener {
            // Handle item click, redirect to detailed race information
            val intent = Intent(context, SeeRaceMainPage::class.java)
            intent.putExtra("raceId", currentRace.raceId)
            intent.putExtra("raceDate", currentRace.date) // Pass the race date to the next activity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return raceList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val raceId: TextView = itemView.findViewById(R.id.raceNameTitle002)
        val raceDate : TextView = itemView.findViewById(R.id.raceYearTitle002)
        val seeResults : Button = itemView.findViewById(R.id.seeRaceResult_Btn_VIEW)
    }
}
