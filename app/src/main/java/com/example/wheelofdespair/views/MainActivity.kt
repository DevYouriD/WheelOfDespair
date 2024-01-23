package com.example.wheelofdespair.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils
import com.example.wheelofdespair.R
import android.widget.ImageView
import android.widget.Button
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var openDbPageButton: Button
    private lateinit var openTestPageButton: Button
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openDbPageButton = findViewById(R.id.openDbPageButton)
        openTestPageButton = findViewById(R.id.openTestPageButton)
        image = findViewById(R.id.imageView)

        openDbPageButton.setOnClickListener {
            val intent = Intent(this, SqliteActivity::class.java)
            startActivity(intent)
        }

        openTestPageButton.setOnClickListener {
            val intent = Intent(this, WheelActivity::class.java)
            startActivity(intent)
        }
    }
}




