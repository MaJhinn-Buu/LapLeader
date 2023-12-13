package com.example.groupactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainMenu_User : AppCompatActivity() {
//This is For Main Menu Spinner and Buttons
    lateinit var recyclerView: RecyclerView
    lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainmenu_userlayout)

        //We'll Replace The Values Below Using The Database Value For Logo
        imageList= arrayOf(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background)
        titleList = arrayOf("Numero Uno", "Numero Dos", "Numero Tres", "Numero Quatro")

        recyclerView = findViewById(R.id.MMLeaderboard_User)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)
        dataList = arrayListOf<DataClass>()
        getData()
    }

    private fun getData() {
        for (i in imageList.indices) {
            var dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }
}