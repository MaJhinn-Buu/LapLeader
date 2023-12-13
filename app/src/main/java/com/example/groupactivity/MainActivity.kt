package com.example.groupactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.graphics.convertTo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    //This is for Login
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dataList = arrayListOf<User_Accounts>()

        var buttonLogin = findViewById<Button>(R.id.button)

        var editUsername = findViewById<EditText>(R.id.editUsername)
        var editPassword = findViewById<EditText>(R.id.editPassword)

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")
        var userDetails = User_Accounts("Isaiah", "123", 1)
        var dataKey = databaseReference.push().getKey()
        databaseReference.child("Users").child(dataKey.toString()).setValue(userDetails)
            .addOnSuccessListener {
                Toast.makeText(this, "Success - ADD", Toast.LENGTH_SHORT).show()
            }

        buttonLogin.setOnClickListener {
            databaseReference.child("Users").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val snapshot = task.result
                    for (e in snapshot.children) {
                        var getPassword = e.child("password").getValue(String::class.java)
                        var getPrivilege = e.child("privilege").getValue(Int::class.java)
                        var getUsername = e.child("username").getValue(String::class.java)

                        var dc = User_Accounts(
                            getUsername.toString(),
                            getPassword.toString(),
                            getPrivilege!!.toInt()
                        )
                        dataList.add(dc)
                    }

                    val passwd = snapshot.child("password").getValue(String::class.java)
                    val priv = snapshot.child("privilege").getValue(Int::class.java)
                    val userName = snapshot.child("username").getValue(String::class.java)

                    val inputUsername = editUsername.text.toString()
                    val inputPassword = editPassword.text.toString()

                    val isUsernameExists = userName?.contains(inputUsername)
                    val isPasswordExists = passwd?.contains(inputPassword)

                    if (isUsernameExists != null && isPasswordExists != null) {
                        if (isUsernameExists && isPasswordExists){
                            if (priv != null) {
                                if (priv == 1){
                                    val intent = Intent(this, MainMenu_Admin::class.java)
                                    startActivity(intent)
                                }

                                else if (priv == 0){
                                    val intent = Intent(this, MainMenu_User::class.java )
                                }
                            }
                        }
                    }




                }
            }
        }
    }
}