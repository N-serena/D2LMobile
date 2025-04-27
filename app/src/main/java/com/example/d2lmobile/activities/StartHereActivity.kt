package com.example.d2lmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R
import com.example.d2lmobile.models.ResourceSection
import com.google.firebase.firestore.FirebaseFirestore

class StartHereActivity : AppCompatActivity() {
    val firestore = FirebaseFirestore.getInstance()

    fun loadSection(sectionName: String, onResult: (List<String>) -> Unit) {
        firestore.collection("start_here")
            .document(sectionName)
            .get()
            .addOnSuccessListener { doc ->
                val links = doc.get("links") as? List<String> // 🆕 directly fetch 'links' field
                onResult(links ?: emptyList())
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error loading $sectionName", Toast.LENGTH_SHORT).show()
            }
    }

    fun openResourcesScreen(sectionTitle: String, items: List<String>) {
        val intent = Intent(this, ResourcesActivity::class.java)
        intent.putExtra("TITLE", sectionTitle)
        intent.putStringArrayListExtra("ITEMS", ArrayList(items))
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_here)

        findViewById<Button>(R.id.btn_welcome).setOnClickListener {
            loadSection("welcome") { links ->
                openResourcesScreen("Welcome", links)
            }
        }

        findViewById<Button>(R.id.btn_syllabus).setOnClickListener {
            loadSection("syllabus") { links ->
                openResourcesScreen("Syllabus", links)
            }
        }

        findViewById<Button>(R.id.btn_lab_report).setOnClickListener {
            loadSection("lab_report") { links ->
                openResourcesScreen("Lab Report", links)
            }
        }

        findViewById<Button>(R.id.btn_project_instruction).setOnClickListener {
            loadSection("project_instruction") { links ->
                openResourcesScreen("Project Instruction", links)
            }
        }
    }

}