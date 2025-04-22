package com.example.d2lmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R

class StartHereActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_here)

        findViewById<Button>(R.id.btn_welcome).setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
        }

        findViewById<Button>(R.id.btn_syllabus).setOnClickListener {
            startActivity(Intent(this, SyllabusActivity::class.java))
        }

        findViewById<Button>(R.id.btn_lab_report).setOnClickListener {
            startActivity(Intent(this, LabReportInstructionActivity::class.java))
        }

        findViewById<Button>(R.id.btn_project_instruction).setOnClickListener {
            startActivity(Intent(this, ProjectInstructionActivity::class.java))
        }
    }
}