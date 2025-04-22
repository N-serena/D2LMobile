package com.example.d2lmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R

class ModuleDetailActivity : AppCompatActivity() {
    private var moduleNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module_detail)

        moduleNumber = intent.getIntExtra("MODULE_NUMBER", 1)

        findViewById<Button>(R.id.btn_slides).setOnClickListener {
            val intent = Intent(this, SlidesActivity::class.java)
            intent.putExtra("MODULE_NUMBER", moduleNumber)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_lab).setOnClickListener {
            val intent = Intent(this, LabActivity::class.java)
            intent.putExtra("MODULE_NUMBER", moduleNumber)
            startActivity(intent)
        }
    }
}