package com.example.groupactivity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditDriversCupPage : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edtstandings_driverscup)

        val edtStanding: Button = findViewById(R.id.btnEdtStandsDriver)
        val backBtn: ImageButton = findViewById(R.id.ESFDbackbutton)

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")

        // Driver Names
        val spinnerDriver1: Spinner = findViewById(R.id.P1Driver_ESD)
        val spinnerDriver2: Spinner = findViewById(R.id.P2Driver_ESD)
        val spinnerDriver3: Spinner = findViewById(R.id.P3Driver_ESD)
        val spinnerDriver4: Spinner = findViewById(R.id.P4Driver_ESD)
        val spinnerDriver5: Spinner = findViewById(R.id.P5Driver_ESD)
        val spinnerDriver6: Spinner = findViewById(R.id.P6Driver_ESD)
        val spinnerDriver7: Spinner = findViewById(R.id.P7Driver_ESD)
        val spinnerDriver8: Spinner = findViewById(R.id.P8Driver_ESD)
        val spinnerDriver9: Spinner = findViewById(R.id.P9Driver_ESD)
        val spinnerDriver10: Spinner = findViewById(R.id.P10Driver_ESD)

        // Team names
        val spinnerTeam1: Spinner = findViewById(R.id.P1Const_ESD)
        val spinnerTeam2: Spinner = findViewById(R.id.P2Const_ESD)
        val spinnerTeam3: Spinner = findViewById(R.id.P3Const_ESD)
        val spinnerTeam4: Spinner = findViewById(R.id.P4Const_ESD)
        val spinnerTeam5: Spinner = findViewById(R.id.P5Const_ESD)
        val spinnerTeam6: Spinner = findViewById(R.id.P6Const_ESD)
        val spinnerTeam7: Spinner = findViewById(R.id.P7Const_ESD)
        val spinnerTeam8: Spinner = findViewById(R.id.P8Const_ESD)
        val spinnerTeam9: Spinner = findViewById(R.id.P9Const_ESD)
        val spinnerTeam10: Spinner = findViewById(R.id.P10Const_ESD)

        val data1 = listOf(
            "Max Verstappen",
            "Sergio Perez",
            "Charles Leclerc",
            "Carlos Sainz",
            "Lewis Hamilton",
            "George Russell",
            "Lando Norris",
            "Oscar Piastri",
            "Fernando Alonso",
            "Lance Stroll",
            "AMADO SAPIT III"
        )

        val data2 = listOf("Red Bull", "Ferrari", "AMG Mercedes", "McLaren", "Aston Martin", "Mob Dev")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data1)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, data2)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

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

        edtStanding.setOnClickListener {
            // Creating StandingItem objects for each spinner selection
            val standingItem1 = StandingItem(spinnerDriver1.selectedItem.toString(), spinnerTeam1.selectedItem.toString())
            val standingItem2 = StandingItem(spinnerDriver2.selectedItem.toString(), spinnerTeam2.selectedItem.toString())
            val standingItem3 = StandingItem(spinnerDriver3.selectedItem.toString(), spinnerTeam3.selectedItem.toString())
            val standingItem4 = StandingItem(spinnerDriver4.selectedItem.toString(), spinnerTeam4.selectedItem.toString())
            val standingItem5 = StandingItem(spinnerDriver5.selectedItem.toString(), spinnerTeam5.selectedItem.toString())
            val standingItem6 = StandingItem(spinnerDriver6.selectedItem.toString(), spinnerTeam6.selectedItem.toString())
            val standingItem7 = StandingItem(spinnerDriver7.selectedItem.toString(), spinnerTeam7.selectedItem.toString())
            val standingItem8 = StandingItem(spinnerDriver8.selectedItem.toString(), spinnerTeam8.selectedItem.toString())
            val standingItem9 = StandingItem(spinnerDriver9.selectedItem.toString(), spinnerTeam9.selectedItem.toString())
            val standingItem10 = StandingItem(spinnerDriver10.selectedItem.toString(), spinnerTeam10.selectedItem.toString())

            // Save standings with ID 1 and a list of StandingItem objects
            val standingsMap = mapOf(
                "1" to standingItem1,
                "2" to standingItem2,
                "3" to standingItem3,
                "4" to standingItem4,
                "5" to standingItem5,
                "6" to standingItem6,
                "7" to standingItem7,
                "8" to standingItem8,
                "9" to standingItem9,
                "10" to standingItem10
            )
            databaseReference.child("Standings").setValue(standingsMap)
        }

        backBtn.setOnClickListener {
            // This is to return to Login Page
            try {
                val logIntent = Intent(this, MainMenu_Admin::class.java)
                startActivity(logIntent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class Standings(
    var standingsList: ArrayList<StandingItem> = arrayListOf()
)

data class StandingItem(
    var driverName: String = "",
    var teamName: String = ""
)
