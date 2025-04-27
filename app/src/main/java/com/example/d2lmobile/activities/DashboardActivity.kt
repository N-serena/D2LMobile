package com.example.d2lmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Firebase Auth
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        // Firebase Realtime DB
        dbRef = FirebaseDatabase.getInstance().getReference("users")

        // View bindings
        welcomeText = findViewById(R.id.tv_welcome)
        val btnStartHere = findViewById<Button>(R.id.btn_start_here)
        val btnLearningModules = findViewById<Button>(R.id.btn_learning_modules)

        // Display personalized welcome message
        currentUser?.uid?.let { userId ->
            dbRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val firstName = snapshot.child("firstName").getValue(String::class.java)
                    welcomeText.text = "Welcome, ${firstName ?: "Student"}!"
                }

                override fun onCancelled(error: DatabaseError) {
                    welcomeText.text = "Welcome!"
                }
            })
        }

        // Set button actions
        btnStartHere.setOnClickListener {
            startActivity(Intent(this, StartHereActivity::class.java))
        }

        btnLearningModules.setOnClickListener {
            startActivity(Intent(this, LearningModulesActivity::class.java))
        }
    }
}
