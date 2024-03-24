package com.example.wheelofdespair.views

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.wheelofdespair.R
import com.example.wheelofdespair.sqlite.DataBaseHelper
import com.example.wheelofdespair.sqlite.DataModel
import com.example.wheelofdespair.wheel.WheelHelper

class WheelActivity : AppCompatActivity() {

    private lateinit var openDbPageButton: ImageButton
    private lateinit var itemList: ListView
    private lateinit var wheelHelper: WheelHelper

    private lateinit var dataBaseHelper: DataBaseHelper
    private lateinit var data: ArrayList<DataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel)

        openDbPageButton = findViewById(R.id.openDbPageButton)
        itemList = findViewById(R.id.lv_userList)
        wheelHelper = findViewById(R.id.pieChartView)

        dataBaseHelper = DataBaseHelper(this)
        data = ArrayList(dataBaseHelper.allData)

        val colors = intArrayOf(
            getColor(R.color.wheelColor1),
            getColor(R.color.wheelColor2),
            getColor(R.color.wheelColor3),
            getColor(R.color.wheelColor4),
            getColor(R.color.wheelColor5),
            getColor(R.color.wheelColor6),
            getColor(R.color.wheelColor7),
            getColor(R.color.wheelColor8),
            getColor(R.color.wheelColor9),
            getColor(R.color.wheelColor10),
            getColor(R.color.wheelColor11),
            getColor(R.color.wheelColor12)
        )

        /** Clean code alternative (using modulo)
         *
        var setColors = IntArray(data.size)
        var counter = 0
        for (item in 0 until data.size) {
        setColors[item] = colors[counter % colors.size]
        counter++
        }
         */
        // Dynamically change colors (own solution)
        val setColors = IntArray(data.size)
        var counter = 0
        for (item in 0 until data.size) {
            if (counter == 12) {
                counter = 0
            }
            setColors[item] = colors[counter]
            counter++
        }

        wheelHelper.setItemsAndColors(
            data.size,
            setColors.reversedArray(),
            DataModel.getNamesArray(data)
        )

        var fromDegrees = 0f
        var randomToDegrees = (1080..1800).random().toFloat()
        wheelHelper.setOnClickListener()
        {
            if (fromDegrees < Float.MAX_VALUE && randomToDegrees < Float.MAX_VALUE) {
                playRotateAnimation(wheelHelper, fromDegrees, randomToDegrees)
            } else {
                fromDegrees = 0f
                randomToDegrees = (1080..1800).random().toFloat()
                playRotateAnimation(wheelHelper, fromDegrees, randomToDegrees)
            }
            fromDegrees = randomToDegrees
            randomToDegrees += (1080..1800).random().toFloat()
        }

        openDbPageButton.setOnClickListener {
            val intent = Intent(this, SqliteActivity::class.java)
            startActivity(intent)
        }

        // Modify back-button behavior
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitPopup(this@WheelActivity)
            }
        })
    }

    private fun playRotateAnimation(view: View, fromDegrees: Float, randomToDegrees: Float) {
        val animation = RotateAnimation(
            fromDegrees,
            randomToDegrees,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        animation.duration = 2500
        animation.fillAfter = true
        view.startAnimation(animation)
    }

    private fun showExitPopup(context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.exit_popup, null)

        val btnConfirm = dialogView.findViewById<Button>(R.id.btnConfirm)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)

        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)

        val dialog = dialogBuilder.create()

        btnConfirm.setOnClickListener {
            finishAffinity()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
