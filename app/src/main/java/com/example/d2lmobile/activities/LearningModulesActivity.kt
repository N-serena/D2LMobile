package com.example.d2lmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R

class LearningModulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_modules)

        val buttons = listOf(
            R.id.btn_module1, R.id.btn_module2, R.id.btn_module3,
            R.id.btn_module4, R.id.btn_module5, R.id.btn_module6,
            R.id.btn_module7, R.id.btn_module8, R.id.btn_module9,
            R.id.btn_module10, R.id.btn_module11, R.id.btn_module12, R.id.btn_module13
        )

        for ((index, id) in buttons.withIndex()) {
            findViewById<Button>(id).setOnClickListener {
                val intent = Intent(this, ModuleDetailActivity::class.java)
                intent.putExtra("MODULE_NUMBER", index + 1)
                startActivity(intent)
            }
        }
    }
}
