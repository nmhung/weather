package net.fitken.base.widget

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

class LayoutOutlineProvider internal constructor(var width: Int, var height: Int, var radius: Float) :
    ViewOutlineProvider() {

    override fun getOutline(view: View, outline: Outline) {
        outline.setRoundRect(0, 0, width, height, radius)
    }
}