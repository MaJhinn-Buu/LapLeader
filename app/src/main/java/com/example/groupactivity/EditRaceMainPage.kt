package com.example.groupactivity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

    class EditRaceMainPage : AppCompatActivity() {

        lateinit var databaseReference: DatabaseReference
        lateinit var raceData: RaceDataClass

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.editraceresult)

            val backBtn: ImageButton = findViewById(R.id.EDTRRbackbutton)
            val updateRace: Button = findViewById(R.id.btnEdtRace)

            databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")

            //Driver Names
            val spinnerDriver1: Spinner = findViewById(R.id.P1Driver_ERR)
            val spinnerDriver2: Spinner = findViewById(R.id.P2Driver_ERR)
            val spinnerDriver3: Spinner = findViewById(R.id.P3Driver_ERR)
            val spinnerDriver4: Spinner = findViewById(R.id.P4Driver_ERR)
            val spinnerDriver5: Spinner = findViewById(R.id.P5Driver_ERR)
            val spinnerDriver6: Spinner = findViewById(R.id.P6Driver_ERR)
            val spinnerDriver7: Spinner = findViewById(R.id.P7Driver_ERR)
            val spinnerDriver8: Spinner = findViewById(R.id.P8Driver_ERR)
            val spinnerDriver9: Spinner = findViewById(R.id.P9Driver_ERR)
            val spinnerDriver10: Spinner = findViewById(R.id.P10Driver_ERR)

            //Team names
            val spinnerTeam1: Spinner = findViewById(R.id.P1Const_ERR)
            val spinnerTeam2: Spinner = findViewById(R.id.P2Const_ERR)
            val spinnerTeam3: Spinner = findViewById(R.id.P3Const_ERR)
            val spinnerTeam4: Spinner = findViewById(R.id.P4Const_ERR)
            val spinnerTeam5: Spinner = findViewById(R.id.P5Const_ERR)
            val spinnerTeam6: Spinner = findViewById(R.id.P6Const_ERR)
            val spinnerTeam7: Spinner = findViewById(R.id.P7Const_ERR)
            val spinnerTeam8: Spinner = findViewById(R.id.P8Const_ERR)
            val spinnerTeam9: Spinner = findViewById(R.id.P9Const_ERR)
            val spinnerTeam10: Spinner = findViewById(R.id.P10Const_ERR)

            val data1 = listOf("M. Verstappen",
                "S. Perez",
                "C. Leclerc",
                "C. Sainz",
                "L. Hamilton",
                "G. Russell",
                "L. Norris",
                "O. Piastri",
                "F. Alonso",
                "L. Stroll",
                "A. SAPIT"
            )


            val data2 = listOf("Red Bull", "Ferrari", "AMG Mercedes", "McLaren", "Aston Martin", "Mob Dev")

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data1)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, data2)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerDriver1.adapter = adapter
            spinnerDriver2.adapter = adapter
            spinnerDriver3.adapter = adapter
            spinnerDriver4.adapter = adapter
            spinnerDriver5.adapter = adapter
            spinnerDriver6.adapter = adapter
            spinnerDriver7.adapter = adapter
            spinnerDriver8.adapter = adapter
            spinnerDriver9.adapter = adapter
            spinnerDriver10.adapter = adapter

            spinnerTeam1.adapter = adapter2
            spinnerTeam2.adapter = adapter2
            spinnerTeam3.adapter = adapter2
            spinnerTeam4.adapter = adapter2
            spinnerTeam5.adapter = adapter2
            spinnerTeam6.adapter = adapter2
            spinnerTeam7.adapter = adapter2
            spinnerTeam8.adapter = adapter2
            spinnerTeam9.adapter = adapter2
            spinnerTeam10.adapter = adapter2

            raceData = intent.getParcelableExtra("raceData") ?: RaceDataClass("", 0, "", "", 0, arrayListOf())

            val raceYear: EditText = findViewById(R.id.YearEdt)
            val raceDate: EditText = findViewById(R.id.DateEdt)
            val raceLaps: EditText = findViewById(R.id.LapEdt)
            val raceLoc: EditText = findViewById(R.id.LocationEdt)


            updateRace.setOnClickListener {

                // Map each driver to their respective team
                val driverTeamMap = mapOf(
                    spinnerDriver1.selectedItem.toString() to spinnerTeam1.selectedItem.toString(),
                    spinnerDriver2.selectedItem.toString() to spinnerTeam2.selectedItem.toString(),
                    spinnerDriver3.selectedItem.toString() to spinnerTeam3.selectedItem.toString(),
                    spinnerDriver4.selectedItem.toString() to spinnerTeam4.selectedItem.toString(),
                    spinnerDriver5.selectedItem.toString() to spinnerTeam5.selectedItem.toString(),
                    spinnerDriver6.selectedItem.toString() to spinnerTeam6.selectedItem.toString(),
                    spinnerDriver7.selectedItem.toString() to spinnerTeam7.selectedItem.toString(),
                    spinnerDriver8.selectedItem.toString() to spinnerTeam8.selectedItem.toString(),
                    spinnerDriver9.selectedItem.toString() to spinnerTeam9.selectedItem.toString(),
                    spinnerDriver10.selectedItem.toString() to spinnerTeam10.selectedItem.toString()
                )

                // Populate driverList with drivers and their respective teams
                val driverList = ArrayList<String>()
                driverTeamMap.forEach { (driver, team) ->
                    driverList.add("$driver - $team")
                }

                // Update the existing raceData with the new values
                raceData.year = raceYear.text.toString().toInt()
                raceData.date = raceDate.text.toString()
                raceData.laps = raceLaps.text.toString().toInt()
                raceData.location = raceLoc.text.toString()
                raceData.driverList = driverList

                // Get a reference to the specific Race Id node in Firebase
                val raceIdReference = databaseReference.child("Race Results").child(raceData.raceId)

                // Update the values of each child under the Race Id node
                raceIdReference.child("gpyear").setValue(raceData.year)
                raceIdReference.child("gpdate").setValue(raceData.date)
                raceIdReference.child("gpnumOfLaps").setValue(raceData.laps)
                raceIdReference.child("gplocation").setValue(raceData.location)
                raceIdReference.child("drivers").setValue(raceData.driverList)

                Toast.makeText(this, "Success - UPDATE", Toast.LENGTH_LONG).show()
            }


            backBtn.setOnClickListener {
                    // This is to return to the previous activity
                    try {
                        val logIntent = Intent(this, EditRaceFindPage::class.java)
                        startActivity(logIntent)
                    } catch (e: Exception) {
                        // Handle exception if needed
                    }
                }
            }
        }

