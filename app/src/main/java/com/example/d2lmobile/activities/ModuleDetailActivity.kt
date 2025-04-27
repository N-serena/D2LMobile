package com.example.d2lmobile.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R
import com.example.d2lmobile.models.ModuleLink

class ModuleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val moduleTitle = intent.getStringExtra("MODULE_TITLE") ?: "Module"
        val moduleLinks = intent.getSerializableExtra("MODULE_LINKS") as? ArrayList<ModuleLink> ?: arrayListOf()

        Log.d("ModuleDebug", "Links received: $moduleLinks")
        Toast.makeText(this, "Loaded ${moduleLinks.size} links", Toast.LENGTH_SHORT).show()


        val titleView = findViewById<TextView>(R.id.tv_module_title)
        val linkContainer = findViewById<LinearLayout>(R.id.module_links_layout)

        titleView.text = moduleTitle

        for (link in moduleLinks) {
            if (link.url.isBlank()) continue

            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(32, 32, 32, 32)
                background = resources.getDrawable(R.drawable.link_background, null) // a rounded bg drawable (we'll create)
                elevation = 8f // adds shadow (works on Android 5.0+)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 24 // Space between links
                }
            }

            val icon = ImageView(this).apply {
                setImageResource(getIconForExtension(link.type))
                layoutParams = LinearLayout.LayoutParams(96, 96).apply {
                    rightMargin = 32
                }
            }

            val label = TextView(this).apply {
                text = link.name
                textSize = 18f
                setTextColor(resources.getColor(android.R.color.white))
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link.url))
                    startActivity(intent)
                }
            }

            row.addView(icon)
            row.addView(label)
            linkContainer.addView(row)
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getIconForExtension(ext: String): Int {
        return when (ext) {
            "pdf" -> R.drawable.ic_pdf
            "mp4", "mov", "avi" -> R.drawable.ic_video
            "doc", "docx" -> R.drawable.ic_doc
            "ppt", "pptx" -> R.drawable.ic_ppt
            "xls", "xlsx" -> R.drawable.ic_excel
            "html", "link" -> R.drawable.ic_link
            else -> R.drawable.ic_file // fallback generic file icon
        }
    }
}
