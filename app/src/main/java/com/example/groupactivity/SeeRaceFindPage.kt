package com.example.groupactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SeeRaceFindPage : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var raceList: MutableList<RaceDataClass>
    lateinit var databaseReference: DatabaseReference // Initialize this variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seerace_results_findpage)
        var backBtn: ImageButton = findViewById(R.id.SRFbackbutton)

        recyclerView = findViewById(R.id.SeeRaceResCardView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        raceList = mutableListOf()
        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase") // Initialize here

        databaseReference.child("Race Results").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (raceNode in snapshot.children) {
                    val raceId = raceNode.key.toString()
                    val raceDate = raceNode.child("gpdate").getValue(String::class.java) ?: "N/A"

                    val raceData = RaceDataClass(raceId, 0, raceDate, "", 0, arrayListOf())
                    raceList.add(raceData)
                }

                val adapter = SeeResultsAdapterClass(raceList, this@SeeRaceFindPage)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SeeRaceFindPage", "Data retrieval canceled: $error")
            }
        })

        backBtn.setOnClickListener {
            //This is to return to Login Page
            try {
                var logIntent = Intent(this, MainMenu_User::class.java)
                startActivity(logIntent)
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}