package com.example.wheelofdespair.sqlite

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wheelofdespair.R
import com.example.wheelofdespair.views.MainActivity

class SqliteActivity : AppCompatActivity() {

    private lateinit var et_name: EditText
    private lateinit var et_password: EditText
    private lateinit var btn_add: Button
    private lateinit var btn_deleteAll: Button
    private lateinit var returnToHomePageButton: Button
    private lateinit var lv_userList: ListView

    private lateinit var userArrayAdapter: ArrayAdapter<UserModel>
    private lateinit var dataBaseHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        et_name = findViewById(R.id.et_name)
        et_password = findViewById(R.id.et_password)
        btn_add = findViewById(R.id.btn_add)
        btn_deleteAll = findViewById(R.id.btn_deleteAll)
        returnToHomePageButton = findViewById(R.id.returnToHomePageButton)
        lv_userList = findViewById(R.id.lv_userList)

        dataBaseHelper = DataBaseHelper(this@SqliteActivity)
        updateListView(dataBaseHelper)
        var userModel: UserModel

        btn_add.setOnClickListener {
            try {
                userModel = UserModel(-1, et_name.text.toString(), et_password.text.toString().toString())
                val success: Boolean = dataBaseHelper.addUser(userModel)
                // Toast.makeText(this@SqliteActivity, "Success = $success", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@SqliteActivity, "Invalid format, please fill in all fields", Toast.LENGTH_SHORT).show()
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        lv_userList.setOnItemClickListener { parent, _, position, _ ->
            val selectedUser = parent.getItemAtPosition(position) as UserModel
            dataBaseHelper.deleteUser(selectedUser)
            updateListView(dataBaseHelper)
            // Toast.makeText(this@SqliteActivity, "Deleted $selectedUser", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateListView(dataBaseHelper: DataBaseHelper) {
        userArrayAdapter = ArrayAdapter<UserModel>(
            this@SqliteActivity,
            android.R.layout.simple_list_item_1,
            dataBaseHelper.allUsers
        )
        lv_userList.adapter = userArrayAdapter
    }
}