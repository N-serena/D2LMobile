package com.example.d2lmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        findViewById<Button>(R.id.btn_start_here).setOnClickListener {
            startActivity(Intent(this, StartHereActivity::class.java))
        }

        findViewById<Button>(R.id.btn_learning_modules).setOnClickListener {
            startActivity(Intent(this, LearningModulesActivity::class.java))
        }
    }
}
