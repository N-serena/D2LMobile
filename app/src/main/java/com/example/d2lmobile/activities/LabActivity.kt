package com.example.d2lmobile.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R

class LabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab)

        val moduleNumber = intent.getIntExtra("MODULE_NUMBER", 1)
        findViewById<TextView>(R.id.lab_text).text = "Lab for Module $moduleNumber"
    }
}