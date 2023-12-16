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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EditRaceFindPage : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var raceResult: ArrayList<Race_Results>
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editrace_results_findpage)
        var backBtn: ImageButton = findViewById(R.id.FEDTRRbackbutton)

        recyclerView = findViewById(R.id.FindRaceResCardView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        raceResult = arrayListOf()

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")
        databaseReference.child("Race Results").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val raceList = mutableListOf<RaceDataClass>()

                for (raceNode in snapshot.children) {
                    val raceId = raceNode.key.toString()
                    val raceDate = raceNode.child("gpdate").getValue(String::class.java) ?: "N/A" // Adjust "date" to the correct key
                    val raceYear = raceNode.child("gpyear").getValue(Int::class.java) ?: 0 // Adjust "year" to the correct key
                    val raceLocation = raceNode.child("location").getValue(String::class.java) ?: "N/A" // Adjust "location" to the correct key
                    val raceLaps = raceNode.child("laps").getValue(Int::class.java) ?: 0 // Adjust "laps" to the correct key

                    val driverList = ArrayList<String>()
                    for (driverNode in raceNode.child("driverList").children) {
                        val driver = driverNode.getValue(String::class.java) ?: ""
                        driverList.add(driver)
                    }

                    val raceData = RaceDataClass(raceId, raceYear, raceDate, raceLocation, raceLaps, driverList)
                    raceList.add(raceData)
                }

                val adapter = RaceAdapterClass(raceList, object : RaceAdapterClass.OnEditClickListener {
                    override fun onEditClick(position: Int, raceData: RaceDataClass) {
                        // Handle the edit click for the specified position
                        val editIntent = Intent(this@EditRaceFindPage, EditRaceMainPage::class.java)
                        editIntent.putExtra("raceData", raceData)
                        startActivity(editIntent)
                    }
                })

                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("EditRaceFindPage", "Data retrieval canceled: $error")
            }
        })

        backBtn.setOnClickListener {
            // This is to return to the previous activity
            try {
                val logIntent = Intent(this, MainMenu_Admin::class.java)
                startActivity(logIntent)
            } catch (e: Exception) {
                // Handle exception if needed
            }
        }
    }
}