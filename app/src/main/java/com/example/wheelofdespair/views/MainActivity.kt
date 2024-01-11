package com.example.wheelofdespair.views

import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils
import com.example.wheelofdespair.R
import android.widget.ImageView
import android.widget.Button
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var clockWiseButton: Button
    private lateinit var counterClockWiseButton: Button
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clockWiseButton = findViewById(R.id.clk_rotate_button)
        counterClockWiseButton = findViewById(R.id.anticlk_rotate_button)
        image = findViewById(R.id.imageView)

        // Rotate clockwise
        clockWiseButton.setOnClickListener()
        {
            val rotateClockWise = AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_clockwise
            )
            image.startAnimation(rotateClockWise)
        }

        // Rotate counter clockwise
        counterClockWiseButton.setOnClickListener()
        {
            val rotateCounterClockWise = AnimationUtils.loadAnimation(
                this,
                R.anim.rotate_counterclockwise
            )
            image.startAnimation(rotateCounterClockWise)
        }
    }
}




