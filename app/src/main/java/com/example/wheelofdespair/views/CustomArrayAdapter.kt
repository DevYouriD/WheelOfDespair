package com.example.wheelofdespair.views

import com.example.wheelofdespair.sqlite.DataModel
import com.example.wheelofdespair.R
import android.widget.ArrayAdapter
import android.view.LayoutInflater
import android.widget.TextView
import android.content.Context
import android.view.ViewGroup
import android.view.View

class CustomArrayAdapter(context: Context, resource: Int, textViewResourceId: Int, objects: List<DataModel>) :
    ArrayAdapter<DataModel>(context, resource, textViewResourceId, objects) {

    private class ViewHolder {
        lateinit var textViewInput: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val rowView: View

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.custom_list_view, parent, false)

            viewHolder = ViewHolder()
            viewHolder.textViewInput = rowView.findViewById(R.id.textViewInput)

            rowView.tag = viewHolder
        } else {
            rowView = convertView
            viewHolder = rowView.tag as ViewHolder
        }
        viewHolder.textViewInput.text = getItem(position)?.input ?: ""

        return rowView
    }
}