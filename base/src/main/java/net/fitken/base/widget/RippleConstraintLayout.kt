package net.fitken.base.widget

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import net.fitken.base.R
import net.fitken.base.util.RippleUtil

class RippleConstraintLayout : ConstraintLayout {

    companion object {
        private const val CORNER_RADIUS = 0
        private const val STROKE_WIDTH = 0
        private const val DASH_WIDTH = 0
        private const val DASH_GAP = 0
        private const val NORMAL_COLOR = Color.WHITE
        private const val IS_BACKGROUND = true
        private const val RIPPLE_COLOR = Color.LTGRAY
    }

    private var mIsBackground = false
    private var mCornerRadius = 0
    private var mLeftTopCornerRadius = 0
    private var mRightTopCornerRadius = 0
    private var mLeftBottomCornerRadius = 0
    private var mRightBottomCornerRadius = 0
    private var mStrokeWidth = 0
    private var mStrokeColor = 0
    private var mRippleColor = 0
    private var mNormalColor = 0
    private var mDashWidth = 0f
    private var mDashGap = 0f


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.RippleConstraintLayout, defStyleAttr, 0)

        mIsBackground = a.getBoolean(R.styleable.RippleConstraintLayout_rcl_is_background, IS_BACKGROUND)

        mCornerRadius = a.getDimensionPixelSize(R.styleable.RippleConstraintLayout_rcl_corners, CORNER_RADIUS)
        mLeftTopCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleConstraintLayout_rcl_left_top_corner, CORNER_RADIUS)
        mRightTopCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleConstraintLayout_rcl_right_top_corner, CORNER_RADIUS)
        mLeftBottomCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleConstraintLayout_rcl_left_bottom_corner, CORNER_RADIUS)
        mRightBottomCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleConstraintLayout_rcl_right_bottom_corner, CORNER_RADIUS)
        mStrokeWidth = a.getDimensionPixelSize(R.styleable.RippleConstraintLayout_rcl_stroke_width, STROKE_WIDTH)
        mStrokeColor = a.getColor(R.styleable.RippleConstraintLayout_rcl_stroke_color, RIPPLE_COLOR)
        mRippleColor = a.getColor(R.styleable.RippleConstraintLayout_rcl_ripple_color, RIPPLE_COLOR)
        mNormalColor = a.getColor(R.styleable.RippleConstraintLayout_rcl_normal_color, NORMAL_COLOR)
        mDashWidth = a.getDimensionPixelSize(R.styleable.RippleConstraintLayout_rcl_dash_width, DASH_WIDTH).toFloat()
        mDashGap = a.getDimensionPixelSize(R.styleable.RippleConstraintLayout_rcl_dash_gap, DASH_GAP).toFloat()

        a.recycle()

        setBackground()
    }

    private fun setBackground() {
        if (mIsBackground) {
            background = if (mStrokeWidth == 0) {
                RippleUtil.getRippleDrawable(
                        mNormalColor, mRippleColor, mCornerRadius.toFloat(),
                        mLeftTopCornerRadius.toFloat(), mRightTopCornerRadius.toFloat(),
                        mLeftBottomCornerRadius.toFloat(), mRightBottomCornerRadius.toFloat(), mDashWidth, mDashGap
                )
            } else {
                RippleUtil.getRippleStrokeDrawable(
                        mNormalColor,
                        mRippleColor,
                        mCornerRadius.toFloat(),
                        mLeftTopCornerRadius.toFloat(), mRightTopCornerRadius.toFloat(),
                        mLeftBottomCornerRadius.toFloat(), mRightBottomCornerRadius.toFloat(),
                        mStrokeWidth,
                        mStrokeColor, mDashWidth, mDashGap
                )
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
        }
    }


    fun bindNormalColor(color: Int) {
        mNormalColor = color
        setBackground()
        invalidate()
    }
}