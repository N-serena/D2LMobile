package com.example.d2lmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerBtn = findViewById<Button>(R.id.btn_register)
        val loginLink = findViewById<Button>(R.id.btn_back_to_login)

        registerBtn.setOnClickListener {
            // Add registration logic later
            startActivity(Intent(this, LoginActivity::class.java))
        }

        loginLink.setOnClickListener {
            finish()
        }
    }
}