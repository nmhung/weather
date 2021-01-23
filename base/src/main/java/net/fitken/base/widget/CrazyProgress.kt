package net.fitken.base.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import net.fitken.rose.Rose


class CrazyProgress : View {

    companion object {
        const val ROTATE_DURATION: Long = 500
        const val END_VALUE_ROTATE = 450F
        const val START_VALUE_ROTATE = 0F
        const val SWEEP_STEP_VALUE = 10
        const val END_VALUE_SHAKE_END = 810F
        const val SHAKE_END_DURATION: Long = 400
        const val MIN_VALUE_SWEEP = 90F
        const val MAX_VALUE_SWEEP = 300F
        const val START_VALUE_SHAKE_START = 90F
        const val END_VALUE_SHAKE_START = 0F
        const val SHAKE_START_DURATION: Long = 600
    }


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private var mSize: Int = 0
    private lateinit var mPaint: Paint
    private lateinit var mPaintBackGroundCircle: Paint
    private lateinit var mRect: RectF
    private var mStartAngle: Float = 90F
    private var mIndeterminateSweep: Float = 90F
    private var mShakeStartAnimator: ValueAnimator? = null
    private var mShakeEndAnimator: ValueAnimator? = null
    private var mRotateAnimator: ValueAnimator? = null
    private var mIsDetached = false

    private fun init() {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintBackGroundCircle = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = Color.parseColor("#5B85FB")
        mPaintBackGroundCircle.color = Color.parseColor("#ECF7FF")
        mPaint.style = Paint.Style.STROKE
        mPaintBackGroundCircle.style = Paint.Style.STROKE
        mPaint.strokeWidth = 8f
        mPaintBackGroundCircle.strokeWidth = 8f
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaintBackGroundCircle.strokeCap = Paint.Cap.ROUND
        mRect = RectF(0F, 0F, 0F, 0F)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val yPadding = paddingTop + paddingBottom
        val xPadding = paddingStart + paddingEnd
        val width = measuredWidth - xPadding
        val height = measuredHeight - yPadding
        mSize = if (width > height) height else width
        mRect.left = (mSize / 10).toFloat()
        mRect.top = (mSize / 10).toFloat()
        mRect.right = mSize.toFloat() - mSize / 10
        mRect.bottom = mSize.toFloat() - mSize / 10
        mPaint.strokeWidth = (width / 10).toFloat()
        mPaintBackGroundCircle.strokeWidth = (width / 10).toFloat()
        setMeasuredDimension(mSize + xPadding, mSize + yPadding)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(mRect.centerX(), mRect.centerY(), mRect.width() / 2, mPaintBackGroundCircle)
        canvas?.drawArc(mRect, mStartAngle, mIndeterminateSweep, false, mPaint)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        shakeArch()
        mIsDetached = false
        Rose.error("attached")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mIsDetached = true
        clearAnimation()
        Rose.error("detached ")
    }

    private fun rotateAndIncreaseSizeArch() {
//        if (mRotateAnimator != null && mRotateAnimator!!.isRunning) {
//            mRotateAnimator!!.cancel()
//            Rose.error("rotate cancel when start")
//        }
        if (mRotateAnimator == null) {
//            Rose.error("rotate is created")
            mRotateAnimator = ValueAnimator.ofFloat(START_VALUE_ROTATE, END_VALUE_ROTATE)
        }
//        Rose.error("rotate started $mStartAngle $mIndeterminateSweep")
        mIndeterminateSweep = MIN_VALUE_SWEEP
        mRotateAnimator!!.duration = ROTATE_DURATION
        mRotateAnimator!!.interpolator = LinearInterpolator()
        mRotateAnimator!!.addUpdateListener { animation ->
            mStartAngle = animation.animatedValue as Float
//            Rose.error("before sweep $mIndeterminateSweep")
            if (mIndeterminateSweep < MAX_VALUE_SWEEP && mStartAngle > START_VALUE_ROTATE + 0) {
                mIndeterminateSweep += SWEEP_STEP_VALUE
            }
//            Rose.error("after sweep $mIndeterminateSweep")
            invalidate()
        }
        mRotateAnimator!!.start()
        mRotateAnimator!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                mRotateAnimator!!.removeAllListeners()
                mRotateAnimator!!.cancel()
                if (!mIsDetached) {
                    shakeEndArch()
                }
            }
        })
    }

    private fun shakeEndArch() {
//        if (mShakeEndAnimator != null && mShakeEndAnimator!!.isRunning) {
//            mShakeEndAnimator!!.cancel()
//        }
        if (mShakeEndAnimator == null) {
            mShakeEndAnimator = ValueAnimator.ofFloat(mStartAngle, END_VALUE_SHAKE_END)
            Rose.error("shake end created")
        }
//        Rose.error("shake end started")
        mShakeEndAnimator!!.duration = SHAKE_END_DURATION
        mShakeEndAnimator!!.interpolator = LinearInterpolator()
        mShakeEndAnimator!!.addUpdateListener { animation ->
            if (animation.animatedValue as Float > mStartAngle) {
                if (mIndeterminateSweep > MIN_VALUE_SWEEP) {
                    mIndeterminateSweep -= SWEEP_STEP_VALUE
                }
            }
            mStartAngle = animation.animatedValue as Float

            invalidate()
        }
        mShakeEndAnimator!!.start()
        mShakeEndAnimator!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                mShakeEndAnimator!!.removeAllListeners()
                mShakeEndAnimator!!.cancel()
                android.os.Handler().postDelayed({
                    if (!mIsDetached) {
                        shakeArch()
                    }
                }, 80L)
            }
        })
    }

    private fun shakeArch() {
//        if (mShakeStartAnimator != null && mShakeStartAnimator!!.isRunning) {
//            mShakeStartAnimator!!.cancel()
//        }
        if (mShakeStartAnimator == null) {
            mShakeStartAnimator = ValueAnimator.ofFloat(START_VALUE_SHAKE_START, END_VALUE_SHAKE_START)
            Rose.error("shake start created")
        }
//        Rose.error("shake start started $mIndeterminateSweep")
        mIndeterminateSweep = MIN_VALUE_SWEEP
//        Rose.error("shake start started $mIndeterminateSweep")

        mShakeStartAnimator!!.duration = SHAKE_START_DURATION
        mShakeStartAnimator!!.interpolator = LinearInterpolator()
        mShakeStartAnimator!!.addUpdateListener { animation ->
            mStartAngle = animation.animatedValue as Float
            invalidate()
        }
        mShakeStartAnimator!!.start()
        mShakeStartAnimator!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                mShakeStartAnimator!!.removeAllListeners()
                mShakeStartAnimator!!.cancel()
                if (!mIsDetached) {
                    rotateAndIncreaseSizeArch()
                }
//                Rose.error("start sweep $mIndeterminateSweep")
            }
        })
    }
}