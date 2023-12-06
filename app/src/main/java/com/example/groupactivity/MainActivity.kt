package com.example.groupactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    //This is for Login
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dataList = arrayListOf<User_Accounts>()

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")
        var userDetails = User_Accounts("Isaiah", "123", 1)
        var dataKey = databaseReference.push().getKey()
        databaseReference.child("Users").child(dataKey.toString()).setValue(userDetails)
            .addOnSuccessListener {
                Toast.makeText(this, "Success - ADD", Toast.LENGTH_SHORT).show()
            }
    }
}