package com.lingo.stock.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.ceil

class ResizableImageView(context: Context, attrs: AttributeSet?) :
    AppCompatImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val drawable: Drawable? = drawable
        if (drawable == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        val imageWidth: Int = drawable.intrinsicWidth
        val imageHeight: Int = drawable.intrinsicHeight
        val viewWidth: Int = MeasureSpec.getSize(widthMeasureSpec)
        val viewHeight: Int = ceil(1.0 * viewWidth * imageHeight / imageWidth).toInt()

        if (imageWidth > viewWidth) {
            Log.e("ResizableImageView", "viewWidth: $viewWidth, imageWidth: $imageWidth")
        } else {
            Log.i("ResizableImageView", "viewWidth: $viewWidth, imageWidth: $imageWidth")
        }

        setMeasuredDimension(viewWidth, viewHeight)
    }
}