package com.example.wheelofdespair.views

import com.example.wheelofdespair.sqlite.DataModel
import com.example.wheelofdespair.R
import android.widget.ArrayAdapter
import android.view.LayoutInflater
import android.widget.TextView
import android.content.Context
import android.view.ViewGroup
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.example.wheelofdespair.sqlite.DataBaseHelper

class CustomArrayAdapter(
    context: Context,
    resource: Int,
    textViewResourceId: Int,
    private val dataList: MutableList<DataModel>
) : ArrayAdapter<DataModel>(context, resource, textViewResourceId, dataList) {

    private class ViewHolder {
        lateinit var textViewInput: TextView
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
            viewHolder.deleteButton = rowView.findViewById(R.id.deleteButton)

            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }

        val data = getItem(position)
        viewHolder.textViewInput.text = data?.input ?: ""

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
}
