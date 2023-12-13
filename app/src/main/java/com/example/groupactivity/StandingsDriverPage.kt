package com.example.groupactivity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class StandingsDriverPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.standings_drivers)
        var backBtn : ImageButton = findViewById(R.id.LBD_backbutton)



        backBtn.setOnClickListener {
            //This is to return to Login Page
            try {
                var logIntent = Intent(this, MainMenu_User::class.java)
                startActivity(logIntent)
            } catch (e: Exception) {

            }
        }

    }
}