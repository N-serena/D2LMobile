package com.example.d2lmobile.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.d2lmobile.R

class ResourcesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra("TITLE") ?: "Resources"
        val items = intent.getStringArrayListExtra("ITEMS") ?: arrayListOf()

        val titleView = findViewById<TextView>(R.id.tv_resources_title)
        val resourceList = findViewById<LinearLayout>(R.id.resource_list)

        titleView.text = title

        for (url in items) {
            val btn = Button(this).apply {
                text = url.substringAfterLast("/") // Show only filename
                textSize = 16f
                setPadding(20, 40, 20, 40)
                background = resources.getDrawable(R.drawable.link_background, null) // Rounded background
                setTextColor(resources.getColor(android.R.color.white))

                setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(browserIntent)
                }
            }

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 24
            }
            resourceList.addView(btn, layoutParams)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}