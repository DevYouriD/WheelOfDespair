package com.example.wheelofdespair.views

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.wheelofdespair.R

class WinnerPopup(
    private val context: Context
) {
    private lateinit var dialog: AlertDialog

    fun showWinner(winnerName: String) {
        // Inflate the custom layout for the popup
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.winner_popup, null)

        // Initialize the TextView and Button from the layout
        val winnerTextView = dialogView.findViewById<TextView>(R.id.winnerTextView)
        val btnClose = dialogView.findViewById<Button>(R.id.btnClose)

        // Set the winner's name
        winnerTextView.text = "Winner: $winnerName"

        // Create the AlertDialog
        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)

        // Set up the close button listener
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        // Show the dialog
        dialog = dialogBuilder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        // Apply fade-in animation
        val window = dialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.attributes?.dimAmount = 0.6f // Dim background
        window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        // Fade in animation
        val fadeIn = ObjectAnimator.ofFloat(dialogView, "alpha", 0f, 1f)
        fadeIn.duration = 300
        fadeIn.start()
    }
}
