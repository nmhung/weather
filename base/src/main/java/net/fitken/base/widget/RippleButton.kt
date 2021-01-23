package net.fitken.base.widget

import android.animation.AnimatorInflater
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import net.fitken.base.R
import net.fitken.base.util.RippleUtil


class RippleButton : AppCompatButton {

    companion object {
        private const val CORNER_RADIUS = 0
        private const val STROKE_WIDTH = 0
        private const val NORMAL_COLOR = Color.WHITE
        private const val RIPPLE_COLOR = Color.LTGRAY
    }

    private var mCornerRadius: Int
    private lateinit var mViewOutline: LayoutOutlineProvider
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
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    ) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.RippleButton, defStyleAttr, 0)

        mCornerRadius = a.getDimensionPixelSize(R.styleable.RippleButton_rb_corners, CORNER_RADIUS)
        mLeftTopCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleButton_rb_left_top_corner, CORNER_RADIUS)
        mRightTopCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleButton_rb_right_top_corner, CORNER_RADIUS)
        mLeftBottomCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleButton_rb_left_bottom_corner, CORNER_RADIUS)
        mRightBottomCornerRadius =
                a.getDimensionPixelSize(R.styleable.RippleButton_rb_right_bottom_corner, CORNER_RADIUS)
        mStrokeWidth =
                a.getDimensionPixelSize(R.styleable.RippleButton_rb_stroke_width, STROKE_WIDTH)
        mStrokeColor = a.getColor(R.styleable.RippleButton_rb_stroke_color, RIPPLE_COLOR)
        mNormalColor = a.getColor(R.styleable.RippleButton_rb_normal_color, NORMAL_COLOR)
        mRippleColor = a.getColor(R.styleable.RippleButton_rb_ripple_color, RIPPLE_COLOR)

        a.recycle()

        setBackground()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = context.resources.getDimension(R.dimen.positive_7dp)
            val stateListAnimator = AnimatorInflater
                    .loadStateListAnimator(context, R.animator.lift_on_touch)
            setStateListAnimator(stateListAnimator)
        }
    }

    private fun setBackground() {
        background = if (mStrokeWidth == 0) {
            RippleUtil.getRippleDrawable(
                    mNormalColor,
                    mRippleColor,
                    mCornerRadius.toFloat(),
                    mLeftTopCornerRadius.toFloat(),
                    mRightTopCornerRadius.toFloat(),
                    mLeftBottomCornerRadius.toFloat(),
                    mRightBottomCornerRadius.toFloat(),
                    mDashWidth,
                    mDashGap
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
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mViewOutline = LayoutOutlineProvider(w, h, mCornerRadius.toFloat())
            outlineProvider = mViewOutline
        }
    }

    fun bindNormalColor(color: Int) {
        mNormalColor = color
        setBackground()
        invalidate()
    }
}