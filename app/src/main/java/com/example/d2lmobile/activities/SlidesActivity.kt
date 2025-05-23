package com.example.d2lmobile.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R

class SlidesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slides)

        val moduleNumber = intent.getIntExtra("MODULE_NUMBER", 1)
        findViewById<TextView>(R.id.slides_text).text = "Slides for Module $moduleNumber"
    }
}