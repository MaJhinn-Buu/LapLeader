package com.example.groupactivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(var dataList: ArrayList<DataClass>) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView =
            LayoutInflater.from(p0.context).inflate(R.layout.leaderboardcard_layout, p0, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(p0: ViewHolderClass, p1: Int) {
        val currentItems = dataList[p1]
        p0.rvTitle.text = currentItems.driverName
        p0.rvTeamName.text = currentItems.teamName

        // Dynamically set the ImageView based on the team name
        val teamLogo = when (currentItems.teamName.toLowerCase()) {
            "red bull" -> R.drawable.redbulllogo
            "mclaren" -> R.drawable.mclarenlogo
            "aston martin" -> R.drawable.astonmartinlogo
            "ferrari" -> R.drawable.ferrarilogo
            "amg mercedes" -> R.drawable.mercedeslogo
            else -> R.drawable.f1logo // Provide a default logo if team name is not recognized
        }

        p0.rvImage.setImageResource(teamLogo)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rvImage: ImageView = itemView.findViewById(R.id.TeamLogo)
        var rvTitle: TextView = itemView.findViewById(R.id.raceNameTitle)
        var rvTeamName: TextView = itemView.findViewById(R.id.teamNameTitle)
    }
}
