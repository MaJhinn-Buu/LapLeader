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

class AddRaceResultPage : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addraceresult)
        var backBtn : ImageButton = findViewById(R.id.ADDRRbackbutton)
        var addRace : Button = findViewById(R.id.btnAddRace)

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")

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

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data1)
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


        addRace.setOnClickListener {

//            var raceDetails = Race_Results()
//            var usersReference = databaseReference.child("Race Results")
//            var newRecordReference = usersReference.push()
//            newRecordReference.setValue(raceDetails)
//            var dataKey = newRecordReference.key

        }

        backBtn.setOnClickListener {
            //This is to return to Login Page
            try {
                var logIntent = Intent(this, MainMenu_Admin::class.java)
                startActivity(logIntent)
            } catch (e: Exception) {

            }
        }

    }
}