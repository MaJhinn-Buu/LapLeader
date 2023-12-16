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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddRaceResultPage : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addraceresult)
        val backBtn : ImageButton = findViewById(R.id.ADDRRbackbutton)
        val addRace : Button = findViewById(R.id.btnAddRace)

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")

        //Driver Names
        val spinnerDriver1: Spinner = findViewById(R.id.P1Driver_ARR)
        val spinnerDriver2: Spinner = findViewById(R.id.P2Driver_ARR)
        val spinnerDriver3: Spinner = findViewById(R.id.P3Driver_ARR)
        val spinnerDriver4: Spinner = findViewById(R.id.P4Driver_ARR)
        val spinnerDriver5: Spinner = findViewById(R.id.P5Driver_ARR)
        val spinnerDriver6: Spinner = findViewById(R.id.P6Driver_ARR)
        val spinnerDriver7: Spinner = findViewById(R.id.P7Driver_ARR)
        val spinnerDriver8: Spinner = findViewById(R.id.P8Driver_ARR)
        val spinnerDriver9: Spinner = findViewById(R.id.P9Driver_ARR)
        val spinnerDriver10: Spinner = findViewById(R.id.P10Driver_ARR)

        //Team names
        val spinnerTeam1: Spinner = findViewById(R.id.P1Const_ARR)
        val spinnerTeam2: Spinner = findViewById(R.id.P2Const_ARR)
        val spinnerTeam3: Spinner = findViewById(R.id.P3Const_ARR)
        val spinnerTeam4: Spinner = findViewById(R.id.P4Const_ARR)
        val spinnerTeam5: Spinner = findViewById(R.id.P5Const_ARR)
        val spinnerTeam6: Spinner = findViewById(R.id.P6Const_ARR)
        val spinnerTeam7: Spinner = findViewById(R.id.P7Const_ARR)
        val spinnerTeam8: Spinner = findViewById(R.id.P8Const_ARR)
        val spinnerTeam9: Spinner = findViewById(R.id.P9Const_ARR)
        val spinnerTeam10: Spinner = findViewById(R.id.P10Const_ARR)

        val data1 = listOf("Max Verstappen",
            "Sergio Perez",
            "Charles Leclerc",
            "Carlos Sainz",
            "Lewis Hamilton",
            "George Russell",
            "Lando Norris",
            "Oscar Piastri",
            "Fernando Alonso",
            "Lance Stroll")


        val data2 = listOf("Red Bull", "Ferrari", "AMG Mercedes", "McLaren", "Aston Martin")

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

        val raceYear : EditText = findViewById(R.id.YearAdd)
        val raceDate : EditText = findViewById(R.id.DateAdd)
        val raceLaps : EditText = findViewById(R.id.LapAdd)
        val raceLoc : EditText = findViewById(R.id.LocationAdd)

        addRace.setOnClickListener {

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


            val raceDetails = Race_Results(raceYear.text.toString().toInt(),
                                            raceDate.text.toString(),
                                            raceLoc.text.toString(),
                                            raceLaps.text.toString().toInt(),
                                            driverList
                                            )

            databaseReference.child("Race Results").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children
                    var childCount = 0
                    for (i in snapshot.children) {
                        childCount++
                    }
                    snapshot.child("Race Number $childCount").ref.setValue(raceDetails)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
                    Toast.makeText(this, "Success - ADD", Toast.LENGTH_LONG).show()
        }

        backBtn.setOnClickListener {
            //This is to return to Login Page
            try {
                val logIntent = Intent(this, MainMenu_Admin::class.java)
                startActivity(logIntent)
            } catch (e: Exception) {

            }
        }

    }
}