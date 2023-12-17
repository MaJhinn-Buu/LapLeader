package com.example.groupactivity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StandingsDriverPage : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var databaseReference: DatabaseReference
    lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.standings_drivers)
        var backBtn : ImageButton = findViewById(R.id.LBD_backbutton)

        imageList= arrayOf(R.drawable.ferrarilogo, R.drawable.mercedeslogo, R.drawable.mclarenlogo, R.drawable.astonmartinlogo)
        titleList = arrayOf("Numero Uno", "Numero Dos", "Numero Tres", "Numero Quatro")

        recyclerView = findViewById(R.id.FullConstLB)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)
        dataList = arrayListOf<DataClass>()
        getData()



        backBtn.setOnClickListener {
            //This is to return to Login Page
            try {
                var logIntent = Intent(this, MainMenu_User::class.java)
                startActivity(logIntent)
            } catch (e: Exception) {

            }
        }

    }
    private fun getData() {
        // Fetch the first 5 standings from the database
        val databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase").child("Standings")
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val standingsList = ArrayList<StandingItem>()
                for (childSnapshot in dataSnapshot.children) {
                    val standingItem = childSnapshot.getValue(StandingItem::class.java)
                    standingItem?.let { standingsList.add(it) }
                }

                // Display the first 5 standings
                val top10Standings = standingsList.take(10)
                for (standing in top10Standings) {
                    val dataClass = DataClass(R.drawable.ic_launcher_background, standing.driverName, standing.teamName)
                    dataList.add(dataClass)
                }

                recyclerView.adapter = AdapterClass(dataList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }
}



