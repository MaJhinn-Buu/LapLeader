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
    lateinit var databaseReference : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edtstandings_driverscup)
        val edtStanding : Button = findViewById(R.id.btnEdtStandsDriver)
        val backBtn : ImageButton = findViewById(R.id.ESFDbackbutton)

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")

        //Driver Names
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

        //Team names
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

        val data1 = listOf("M. Verstappen",
            "S. Perez",
            "C. Leclerc",
            "C. Sainz",
            "L. Hamilton",
            "G. Russell",
            "L. Norris",
            "O. Piastri",
            "F. Alonso",
            "L. Stroll")

        val driverList : ArrayList<String> = arrayListOf("M. Verstappen",
            "S. Perez",
            "C. Leclerc",
            "C. Sainz",
            "L. Hamilton",
            "G. Russell",
            "L. Norris",
            "O. Piastri",
            "F. Alonso",
            "L. Stroll")


        val data2 = listOf("Red Bull", "Ferrari", "AMG Mercedes", "McLaren", "Aston Martin")

        val teamList : ArrayList<String> = arrayListOf("Red Bull", "Ferrari", "AMG Mercedes", "McLaren", "Aston Martin")

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


        edtStanding.setOnClickListener {
            val standings = Drivers(driverList, teamList)
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
