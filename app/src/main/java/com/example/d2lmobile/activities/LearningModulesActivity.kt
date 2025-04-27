package com.example.d2lmobile.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R
import com.example.d2lmobile.models.ModuleLink
import com.google.firebase.firestore.FirebaseFirestore

class LearningModulesActivity : AppCompatActivity() {

    private lateinit var moduleLayout: LinearLayout
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_modules)

        moduleLayout = findViewById(R.id.module_list_layout)

        firestore.collection("modules")
            .get()
            .addOnSuccessListener { result ->
                val modules = result.map { doc ->
                    Pair(doc.id.trim(), doc)
                }

                val sortedModules = modules.sortedBy { (id, _) ->
                    val numberPart = id.substringAfter('L').substringBefore(':').trim()
                    numberPart.toIntOrNull() ?: Int.MAX_VALUE
                }

                for ((moduleTitle, doc) in sortedModules) {
                    val button = Button(this).apply {
                        text = moduleTitle
                        textSize = 16f
                        setPadding(20, 40, 20, 40)
                        background = GradientDrawable().apply {
                            shape = GradientDrawable.RECTANGLE
                            cornerRadius = 50f
                            setColor(Color.parseColor("#2196F3"))
                        }
                        setTextColor(Color.WHITE)
                        setOnClickListener {
                            // 🛠 Correctly get Links field
                            val linksArray = doc.get("Links") as? List<HashMap<String, Any>> ?: emptyList()
                            val links = ArrayList(
                                linksArray.map { map ->
                                    ModuleLink(
                                        name = map["name"] as? String ?: "",
                                        url = map["url"] as? String ?: "",
                                        type = map["type"] as? String ?: ""
                                    )
                                }
                            )

                            val intent = Intent(this@LearningModulesActivity, ModuleDetailActivity::class.java)
                            intent.putExtra("MODULE_TITLE", moduleTitle)
                            intent.putExtra("MODULE_LINKS", links) // ✅ Pass the correct 'links' list
                            startActivity(intent)
                        }
                    }

                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        bottomMargin = 24
                    }
                    moduleLayout.addView(button, layoutParams)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to load modules", Toast.LENGTH_SHORT).show()
            }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
