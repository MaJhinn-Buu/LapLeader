package com.example.groupactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SeeRaceMainPage : AppCompatActivity() {

    lateinit var raceId: String
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seerace_results_main)
        val backBtn: ImageButton = findViewById(R.id.SRM_backbutton)

        // Retrieve data from the intent
        raceId = intent.getStringExtra("raceId") ?: ""

        // Initialize database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")

        // Call method to load race details
        loadRaceDetails()

        backBtn.setOnClickListener {
            // This is to return to Login Page
            try {
                val logIntent = Intent(this, MainMenu_User::class.java)
                startActivity(logIntent)
            } catch (e: Exception) {
                // Handle exception
                Log.e("SeeRaceMainPage", "Error navigating to MainMenu_User: $e")
            }
        }
    }

    private fun loadRaceDetails() {
        // Reference to the selected race
        val raceReference = databaseReference.child("Race Results").child(raceId)

        // Retrieve race details
        raceReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    try {
                        // Access race details
                        val gpLocation = snapshot.child("gplocation").getValue(String::class.java) ?: ""
                        val gpyear = snapshot.child("gpyear").getValue(Int::class.java) ?: 0
                        val raceDate = snapshot.child("gpdate").getValue(String::class.java) ?: ""
                        val lapCount = snapshot.child("gpnumOfLaps").getValue(Int::class.java) ?: 0

                        // Update GPTitle
                        val gpTitleTextView: TextView = findViewById(R.id.GPTitle)
                        gpTitleTextView.text = "$gpyear $gpLocation GP"

                        // Update LapCount
                        val lapCountTextView: TextView = findViewById(R.id.LapCount)
                        lapCountTextView.text = "$lapCount Laps"

                        // Update RaceDate
                        val raceDateTextView: TextView = findViewById(R.id.RaceDate)
                        raceDateTextView.text = raceDate

                        // Separate driver names and team names
                        val driverNames = ArrayList<String>()
                        val teamNames = ArrayList<String>()
                        val driversSnapshot = snapshot.child("drivers")

                        driversSnapshot.children.forEach { driverTeamSnapshot ->
                            val driverTeam = driverTeamSnapshot.value as String
                            val parts = driverTeam.split(" - ")
                            if (parts.size == 2) {
                                driverNames.add(parts[0])
                                teamNames.add(parts[1])
                            }
                        }

                        // Update driver TextViews
                        updateDriverTextView(R.id.P1_Driver, driverNames, 0)
                        updateDriverTextView(R.id.P2_Driver, driverNames, 1)
                        updateDriverTextView(R.id.P3_Driver, driverNames, 2)
                        updateDriverTextView(R.id.P4_Driver, driverNames, 3)
                        updateDriverTextView(R.id.P5_Driver, driverNames, 4)
                        updateDriverTextView(R.id.P6_Driver, driverNames, 5)
                        updateDriverTextView(R.id.P7_Driver, driverNames, 6)
                        updateDriverTextView(R.id.P8_Driver, driverNames, 7)
                        updateDriverTextView(R.id.P9_Driver, driverNames, 8)
                        updateDriverTextView(R.id.P10_Driver, driverNames, 9)


                        // Update team TextViews
                        updateTeamTextView(R.id.P1_Const, teamNames, 0)
                        updateTeamTextView(R.id.P2_Const, teamNames, 1)
                        updateTeamTextView(R.id.P3_Const, teamNames, 2)
                        updateTeamTextView(R.id.P4_Const, teamNames, 3)
                        updateTeamTextView(R.id.P5_Const, teamNames, 4)
                        updateTeamTextView(R.id.P6_Const, teamNames, 5)
                        updateTeamTextView(R.id.P7_Const, teamNames, 6)
                        updateTeamTextView(R.id.P8_Const, teamNames, 7)
                        updateTeamTextView(R.id.P9_Const, teamNames, 8)
                        updateTeamTextView(R.id.P10_Const, teamNames, 9)

                    } catch (e: Exception) {
                        Log.e("SeeRaceMainPage", "Error updating race details: $e")
                    }
                } else {
                    Log.e("SeeRaceMainPage", "Race data not found for raceId: $raceId")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SeeRaceMainPage", "Data retrieval canceled: $error")
            }
        })
    }

    private fun updateDriverTextView(viewId: Int, driverNames: List<String>, position: Int) {
        val driverTextView: TextView = findViewById(viewId)
        driverTextView.text = if (position < driverNames.size) driverNames[position] else ""
    }

    private fun updateTeamTextView(viewId: Int, teamNames: List<String>, position: Int) {
        val teamTextView: TextView = findViewById(viewId)
        teamTextView.text = if (position < teamNames.size) teamNames[position] else ""
    }
}