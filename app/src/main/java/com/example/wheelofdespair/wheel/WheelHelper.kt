package com.example.wheelofdespair.wheel

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.compose.ui.text.toUpperCase
import kotlin.math.cos
import kotlin.math.sin

class WheelHelper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint()
    private var numberOfItems: Int = 0
    private var colors: IntArray = intArrayOf()
    private var data: Array<String> = emptyArray()
    private var textBounds = Rect()

    fun setItemsAndColors(items: Int, colors: IntArray, data: Array<String>) {
        numberOfItems = items
        this.colors = colors
        this.data = data
        invalidate()
    }

    private val textPaint: Paint = Paint().apply {
        color = Color.WHITE
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        isAntiAlias = true

        // Set up multiple text shadows to create a stronger border effect
        setShadowLayer(8f, 0f, 0f, Color.BLACK) // First layer: 8px black shadow
        setShadowLayer(12f, 0f, 0f, Color.BLACK) // Second layer: 12px black shadow
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (width.coerceAtMost(height) / 2f) - 20

        if (numberOfItems > 0) {
            val angle = 360f / numberOfItems
            var counter = 0
            for (i in 0 until numberOfItems) {
                // Draw circle
                if (counter < numberOfItems) {
                    paint.color = colors[i]
                    counter++
                }else {
                    counter = 0
                    paint.color = colors[i]
                }

                canvas.drawArc(
                    centerX - radius,
                    centerY - radius,
                    centerX + radius,
                    centerY + radius,
                    i * angle,
                    angle,
                    true,
                    paint
                )

                // Draw the text
                val distanceToCenter = 0.93
                val textRotation = i * angle + angle / 2.0

                // Text size based on number of items
                val textSize = calculateTextSize(numberOfItems)

                textPaint.textSize = textSize

                val text = data[i]
                textPaint.getTextBounds(text, 0, text.length, textBounds)

                val textX = centerX + (radius * distanceToCenter) * cos(Math.toRadians(textRotation)) - textBounds.width() / 2
                val textY = centerY + (radius * distanceToCenter) * sin(Math.toRadians(textRotation)) + textBounds.height() / 2

                canvas.save()
                canvas.rotate(textRotation.toFloat(),
                    (textX + textBounds.width() / 2).toFloat(), (textY - textBounds.height() / 2).toFloat()
                )
                canvas.drawText(text, textX.toFloat(), textY.toFloat(), textPaint)
                canvas.restore()
            }
        }
    }

    private fun calculateTextSize(numberOfItems: Int): Float {
        val baseTextSize = 80f
        val multiplier = 0.5f

        var textSize = baseTextSize - numberOfItems * multiplier

        // Min
        val minTextSize = 50f
        textSize = maxOf(textSize, minTextSize)

        // Max
        val maxTextSize = 100f
        textSize = minOf(textSize, maxTextSize)

        return textSize
    }
}