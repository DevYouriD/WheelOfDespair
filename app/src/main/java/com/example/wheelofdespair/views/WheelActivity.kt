package com.example.wheelofdespair.views

import com.example.wheelofdespair.wheel.WheelHelper
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.RotateAnimation
import android.view.animation.Animation
import com.example.wheelofdespair.R
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.wheelofdespair.sqlite.DataBaseHelper
import com.example.wheelofdespair.sqlite.DataModel

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
        //data = ArrayList(dataBaseHelper.allData)

        // TODO: Replace prefilled list with user input list

        val data = arrayOf(
            "Item 1", "Item 2", "Item 3", "Item 4",
            "Item 5", "Item 6", "Item 7", "Item 8",
            "Item 9", "Item 10", "Item 11", "Item 12"
        )

        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, DataModel.getNamesArray(data))
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        itemList.adapter = adapter

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

        //wheelHelper.setItemsAndColors(data.size, colors.reversedArray(), DataModel.getNamesArray(data))
        wheelHelper.setItemsAndColors(data.size, colors.reversedArray(), data)

        var fromDegrees = 0f
        var randomToDegrees = (1080..1800).random().toFloat()
        wheelHelper.setOnClickListener()
        {
            //println("FROM DEGREES: " + fromDegrees + " TO DEGREES: " + randomToDegrees + "\nDIFFERENCE EQUALS: " + (randomToDegrees - fromDegrees))

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
}
