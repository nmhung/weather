package net.fitken.base.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import net.fitken.base.R
import net.fitken.base.util.RippleUtil

class RippleTextView : AppCompatTextView {

    companion object {
        private const val CORNER_RADIUS = 0
        private const val STROKE_WIDTH = 0
        private const val DASH_WIDTH = 0
        private const val DASH_GAP = 0
        private const val NORMAL_COLOR = Color.WHITE
        private const val RIPPLE_COLOR = Color.LTGRAY
    }

    var cornerRadius = 0
    var leftTopCornerRadius = 0
    var rightTopCornerRadius = 0
    var leftBottomCornerRadius = 0
    var rightBottomCornerRadius = 0
    var strokeWidth = 0
    var strokeColor = 0
    var rippleColor = 0
    var normalColor = 0
    var mDashWidth = 0f
    var mDashGap = 0f

    fun bindNormalColor(color: Int) {
        normalColor = color
        updateBackground()
        invalidate()
    }

    fun updateRippleColor(color: Int) {
        rippleColor = color
        updateBackground()
        invalidate()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.RippleTextView, defStyleAttr, 0)

        cornerRadius = a.getDimensionPixelSize(R.styleable.RippleTextView_rtv_corners, CORNER_RADIUS)
        leftTopCornerRadius = a.getDimensionPixelSize(R.styleable.RippleTextView_rtv_left_top_corner, CORNER_RADIUS)
        rightTopCornerRadius = a.getDimensionPixelSize(R.styleable.RippleTextView_rtv_right_top_corner, CORNER_RADIUS)
        leftBottomCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleTextView_rtv_left_bottom_corner, CORNER_RADIUS)
        rightBottomCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleTextView_rtv_right_bottom_corner, CORNER_RADIUS)
        strokeWidth = a.getDimensionPixelSize(R.styleable.RippleTextView_rtv_stroke_width, STROKE_WIDTH)
        strokeColor = a.getColor(R.styleable.RippleTextView_rtv_stroke_color, RIPPLE_COLOR)
        rippleColor = a.getColor(R.styleable.RippleTextView_rtv_ripple_color, strokeColor)
        normalColor = a.getColor(R.styleable.RippleTextView_rtv_normal_color, NORMAL_COLOR)
        a.recycle()
        updateBackground()
    }

    fun updateBackground() {
        background = if (strokeWidth == 0) {
            RippleUtil.getRippleDrawable(
                    normalColor, rippleColor, cornerRadius.toFloat(),
                    leftTopCornerRadius.toFloat(), rightTopCornerRadius.toFloat(),
                    leftBottomCornerRadius.toFloat(), rightBottomCornerRadius.toFloat(), mDashWidth, mDashGap
            )
        } else {
            RippleUtil.getRippleStrokeDrawable(
                    normalColor,
                    rippleColor,
                    cornerRadius.toFloat(),
                    leftTopCornerRadius.toFloat(), rightTopCornerRadius.toFloat(),
                    leftBottomCornerRadius.toFloat(), rightBottomCornerRadius.toFloat(),
                    strokeWidth,
                    strokeColor, mDashWidth, mDashGap
            )
        }
    }
}