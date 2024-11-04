package com.example.wheelofdespair.views

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.wheelofdespair.R
import com.example.wheelofdespair.sqlite.DataBaseHelper
import com.example.wheelofdespair.sqlite.DataModel

class SqliteActivity : AppCompatActivity() {

    private lateinit var input: EditText

    private lateinit var btn_add: Button
    private lateinit var btn_deleteAll: Button
    
    private lateinit var returnToHomePageButton: ImageButton
    
    private lateinit var lv_userList: ListView

    private lateinit var dataArrayAdapter: ArrayAdapter<DataModel>
    private lateinit var dataBaseHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        input = findViewById(R.id.input)

        // Set maximum input length
        input.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))

        btn_add = findViewById(R.id.btn_add)
        btn_deleteAll = findViewById(R.id.btn_deleteAll)
        returnToHomePageButton = findViewById(R.id.returnToHomePageButton)
        lv_userList = findViewById(R.id.lv_userList)

        dataBaseHelper = DataBaseHelper(this@SqliteActivity)
        updateListView(dataBaseHelper)
        var dataModel: DataModel

        btn_add.setOnClickListener {

            if (input.text.toString() != "") {
                dataModel = DataModel(-1, input.text.toString())
                dataBaseHelper.addData(dataModel)
            } else {
                Toast.makeText(this@SqliteActivity, "Input can not be empty", Toast.LENGTH_SHORT).show()
            }
            val dataBaseHelper = DataBaseHelper(this@SqliteActivity)
            updateListView(dataBaseHelper)
        }

        btn_deleteAll.setOnClickListener {
            dataBaseHelper = DataBaseHelper(this@SqliteActivity)
            dataBaseHelper.clearDb()
            updateListView(dataBaseHelper)
        }

        returnToHomePageButton.setOnClickListener {
            startActivity(Intent(this, WheelActivity::class.java))
        }

        // Modify back-button behavior
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                startActivity(Intent(this@SqliteActivity, WheelActivity::class.java))
                finish()
            }
        })
    }

    fun updateListView(dataBaseHelper: DataBaseHelper) {
      dataArrayAdapter = EditableList(
          this,
          this@SqliteActivity,
          R.layout.custom_list_view,
          R.id.textViewInput,
          dataBaseHelper.allData)
      lv_userList.adapter = dataArrayAdapter
    }
}