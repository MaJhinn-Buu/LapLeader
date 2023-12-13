package com.example.groupactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class MainMenu_Admin : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainmenu_adminlayout)
        var backBtn :ImageButton = findViewById(R.id.ADMINbackbutton)

        //We'll Replace The Values Below Using The Database Value For Logo
        imageList= arrayOf(R.drawable.ferrarilogo, R.drawable.mercedeslogo, R.drawable.mclarenlogo, R.drawable.astonmartinlogo)
        titleList = arrayOf("Numero Uno", "Numero Dos", "Numero Tres", "Numero Quatro")

        recyclerView = findViewById(R.id.MMLeaderboard_Admin)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)
        dataList = arrayListOf<DataClass>()
        getData()


        backBtn.setOnClickListener {
            //This is to return to Login Page
            try {
                var logIntent = Intent(this, MainActivity::class.java)
                startActivity(logIntent)
            } catch (e: Exception) {

            }
        }
    }

    private fun getData() {
        for (i in imageList.indices) {
            var dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }
}