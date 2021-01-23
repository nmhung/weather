package net.fitken.base.widget

import android.animation.AnimatorInflater
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import net.fitken.base.R
import net.fitken.base.util.RippleUtil

class RippleCardView : CardView {

    companion object {
        private const val CORNER_RADIUS = 0
        private const val STROKE_WIDTH = 0
        private const val RIPPLE_COLOR = Color.DKGRAY
    }

    private lateinit var mViewOutline: LayoutOutlineProvider
    private var mCornerRadius = 0
    var mLeftTopCornerRadius = 0
    var mRightTopCornerRadius = 0
    var mLeftBottomCornerRadius = 0
    var mRightBottomCornerRadius = 0
    var mStrokeWidth = 0
    var mStrokeColor = 0
    var mRippleColor = 0
    var mDashWidth = 0f
    var mDashGap = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        if (Build.VERSION.SDK_INT >= 23) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.RippleCardView, defStyleAttr, 0)

            mCornerRadius = a.getDimensionPixelSize(R.styleable.RippleCardView_rcv_corners, CORNER_RADIUS)
            mLeftTopCornerRadius =
                    a.getDimensionPixelSize(R.styleable.RippleCardView_rcv_left_top_corner, CORNER_RADIUS)
            mRightTopCornerRadius =
                    a.getDimensionPixelSize(R.styleable.RippleCardView_rcv_right_top_corner, CORNER_RADIUS)
            mLeftBottomCornerRadius =
                    a.getDimensionPixelSize(R.styleable.RippleCardView_rcv_left_bottom_corner, CORNER_RADIUS)
            mRightBottomCornerRadius =
                    a.getDimensionPixelSize(R.styleable.RippleCardView_rcv_right_bottom_corner, CORNER_RADIUS)
            mStrokeWidth = a.getDimensionPixelSize(R.styleable.RippleCardView_rcv_stroke_width, STROKE_WIDTH)
            mStrokeColor = a.getColor(R.styleable.RippleCardView_rcv_stroke_color, RIPPLE_COLOR)
            mRippleColor = a.getColor(R.styleable.RippleCardView_rcv_ripple_color, RIPPLE_COLOR)

            a.recycle()

            setBackground()
        }
        if (mCornerRadius == 0) {
            mCornerRadius = radius.toInt()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (cardElevation == 0F) {
                cardElevation = context.resources.getDimension(R.dimen.positive_7dp)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val stateListAnimator = AnimatorInflater
                    .loadStateListAnimator(context, R.animator.lift_on_touch)
            setStateListAnimator(stateListAnimator)
        }
    }

    private fun setBackground() {
        foreground = if (mStrokeWidth == 0) {
            RippleUtil.getRippleDrawable(
                    Color.TRANSPARENT, mRippleColor, mCornerRadius.toFloat(),
                    mLeftTopCornerRadius.toFloat(), mRightTopCornerRadius.toFloat(),
                    mLeftBottomCornerRadius.toFloat(), mRightBottomCornerRadius.toFloat(), mDashWidth, mDashGap
            )
        } else {
            RippleUtil.getRippleStrokeDrawable(
                    Color.TRANSPARENT,
                    mRippleColor,
                    mCornerRadius.toFloat(),
                    mLeftTopCornerRadius.toFloat(), mRightTopCornerRadius.toFloat(),
                    mLeftBottomCornerRadius.toFloat(), mRightBottomCornerRadius.toFloat(),
                    mStrokeWidth,
                    mStrokeColor, mDashWidth, mDashGap
            )
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mViewOutline = LayoutOutlineProvider(w, h, mCornerRadius.toFloat())
            outlineProvider = mViewOutline
        }
    }

    fun bindStrokeColor(color: Int) {
        mStrokeColor = color
        setBackground()
        invalidate()
    }
}