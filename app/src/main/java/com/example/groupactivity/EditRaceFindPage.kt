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

class EditRaceFindPage : AppCompatActivity(), RaceAdapterClass.OnEditClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var raceList: MutableList<RaceDataClass>
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editrace_results_findpage)
        var backBtn: ImageButton = findViewById(R.id.FEDTRRbackbutton)

        recyclerView = findViewById(R.id.FindRaceResCardView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        raceList = mutableListOf()

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")
        databaseReference.child("Race Results").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (raceNode in snapshot.children) {
                    val raceId = raceNode.key.toString()
                    val raceDate = raceNode.child("gpdate").getValue(String::class.java) ?: "N/A"

                    val raceData = RaceDataClass(raceId, 0, raceDate, "", 0, arrayListOf())
                    raceList.add(raceData)
                }
                
                val adapter = RaceAdapterClass(raceList, this@EditRaceFindPage)
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

    override fun onEditClick(position: Int, raceData: RaceDataClass) {
        // Handle the edit click for the specified position
        val editIntent = Intent(this, EditRaceMainPage::class.java)
        editIntent.putExtra("raceData", raceData)
        startActivity(editIntent)
    }

    override fun onDeleteClick(position: Int, raceData: RaceDataClass) {
        val raceIdToDelete = raceData.raceId
        databaseReference.child("Race Results").child(raceIdToDelete).removeValue()
        raceList.removeAt(position)
        recyclerView.adapter?.notifyItemRemoved(position)
    }
}