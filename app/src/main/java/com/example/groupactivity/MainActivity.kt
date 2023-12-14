package com.example.groupactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        var buttonLogin = findViewById<Button>(R.id.buttonlogin)

        var editUsername = findViewById<EditText>(R.id.editUsername)
        var editPassword = findViewById<EditText>(R.id.editPassword)

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")
//        var userDetails = User_Accounts("Patrick", "420", 0)
//        var dataKey = databaseReference.push().getKey()
//       databaseReference.child("Users").child(dataKey.toString()).setValue(userDetails)
//           .addOnSuccessListener {
//                Toast.makeText(this, "Success - ADD", Toast.LENGTH_SHORT).show()
//            }

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


                    val inputUsername = editUsername.text.toString()
                    val inputPassword = editPassword.text.toString()

                    if (dataList.isNotEmpty()) {
                        val isUsernameExists = dataList.any { it.Username == inputUsername }
                        val isPasswordExists = dataList.any { it.Password == inputPassword }
                        Log.i(isUsernameExists.toString(), isPasswordExists.toString())
                        if (isUsernameExists && isPasswordExists) {
                            val user = dataList.find { it.Username == inputUsername }
                            Log.i("testing_forever", user?.Privilege.toString())
                            if (user?.Privilege == 1) {
                                // User has privilege 1 (admin)
                                val intent = Intent(this, MainMenu_Admin::class.java)
                                startActivity(intent)
                            } else if (user?.Privilege == 0){
                                // User has other privileges (you might want to handle this case separately)
                                val intent = Intent(this, MainMenu_User::class.java)
                                startActivity(intent)
                            }
                        }
                        else{
                           Toast.makeText(this, "Username or Password is incorrect!",Toast.LENGTH_SHORT).show()
                        }
                    }


                }

            }
        }
    }
}

