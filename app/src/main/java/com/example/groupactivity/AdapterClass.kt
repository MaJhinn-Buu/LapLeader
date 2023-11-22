package com.example.groupactivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterClass(var dataList : ArrayList<DataClass>) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    //We need to find a way to import variables from the database to here
    override  fun onCreateViewHolder(p0: ViewGroup, viewType: Int): AdapterClass.ViewHolderClass {
        var itemView = LayoutInflater.from(p0.context).inflate(R.layout.leaderboardcard_layout, p0, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(p0: ViewHolderClass, p1: Int) {
        var currentItems = dataList[p1]
        p0.rvImage.setImageResource(currentItems.dataImage)
        p0.rvTitle.text = currentItems.dataTitle
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var rvImage : ImageView = itemView.findViewById(R.id.TeamLogo)
        var rvTitle : TextView = itemView.findViewById(R.id.recyclerTitle)
    }
}