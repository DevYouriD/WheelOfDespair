package com.example.wheelofdespair.views

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.wheelofdespair.R
import com.example.wheelofdespair.sqlite.DataBaseHelper
import com.example.wheelofdespair.sqlite.DataModel

class EditableList(
    private val activity: SqliteActivity,
    context: Context,
    resource: Int,
    textViewResourceId: Int,
    private val dataList: MutableList<DataModel>
) : ArrayAdapter<DataModel>(context, resource, textViewResourceId, dataList) {

    private class ViewHolder {
        lateinit var textViewInput: TextView
        lateinit var editButton: ImageButton
        lateinit var deleteButton: ImageButton
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val rowView: View

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.custom_list_view, parent, false)

            viewHolder = ViewHolder()
            viewHolder.textViewInput = rowView.findViewById(R.id.textViewInput)
            viewHolder.editButton = rowView.findViewById(R.id.editButton)
            viewHolder.deleteButton = rowView.findViewById(R.id.deleteButton)

            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }

        val data = getItem(position)
        viewHolder.textViewInput.text = data?.input ?: ""

        viewHolder.editButton.setOnClickListener {
            showEditPopup(data as DataModel)
        }

        viewHolder.deleteButton.setOnClickListener {
            // Delete from Database
            val databaseHelper = DataBaseHelper(context)
            data?.let { databaseHelper.deleteData(it) }

            // Delete from ListView
            dataList.removeAt(position)
            notifyDataSetChanged()
        }

        return rowView
    }

    private fun showEditPopup(data: DataModel) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.edit_data, null)

        val editTextData = view.findViewById<EditText>(R.id.editTextData)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)

        editTextData.setText(data.input)

        val dialogBuilder = AlertDialog.Builder(context)
            .setView(view)

        val dialog = dialogBuilder.create()

        btnSave.setOnClickListener {
            val editedText = editTextData.text.toString()
            if (editedText.isNotEmpty()) {
                val databaseHelper = DataBaseHelper(context)
                databaseHelper.updateData(data, editedText)
                activity.updateListView(databaseHelper)
                dialog.dismiss()
            }
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
