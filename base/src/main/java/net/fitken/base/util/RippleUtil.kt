package net.fitken.base.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build

class RippleUtil {

    companion object {

        fun getRippleStrokeDrawable(
            normalColor: Int,
            rippleColor: Int,
            cornerRadius: Float,
            leftTopCornerRadius: Float = 0f,
            rightTopCornerRadius: Float = 0f,
            leftBottomCornerRadius: Float = 0f,
            rightBottomCornerRadius: Float = 0f,
            strokeWidth: Int,
            strokeColor: Int,
            dashWidth: Float,
            dashGap: Float
        ): Drawable {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                RippleDrawable(ColorStateList.valueOf(rippleColor),
                    getStrokeDrawable(
                        normalColor,
                        cornerRadius,
                        leftTopCornerRadius,
                        rightTopCornerRadius,
                        leftBottomCornerRadius,
                        rightBottomCornerRadius,
                        strokeWidth,
                        strokeColor,
                        dashWidth,
                        dashGap
                    ),
                    getDrawable(rippleColor, cornerRadius))
            } else {
                getDrawableLessThanLollipop(
                    normalColor,
                    cornerRadius,
                    leftTopCornerRadius,
                    rightTopCornerRadius,
                    leftBottomCornerRadius,
                    rightBottomCornerRadius,
                    strokeWidth = strokeWidth,
                    strokeColor = strokeColor,
                    dashWidth = dashWidth,
                    dashGap = dashGap
                )
            }
        }

        fun getRippleDrawable(normalColor: Int, rippleColor: Int, cornerRadius: Float, leftTopCornerRadius: Float,
                              rightTopCornerRadius: Float,
                              leftBottomCornerRadius: Float,
                              rightBottomCornerRadius: Float,
                              dashWidth: Float,
                              dashGap: Float
        ): Drawable {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                RippleDrawable(ColorStateList.valueOf(rippleColor),
                    getDrawable(normalColor, cornerRadius, leftTopCornerRadius, rightTopCornerRadius, leftBottomCornerRadius, rightBottomCornerRadius),
                    getDrawable(rippleColor, cornerRadius, leftTopCornerRadius, rightTopCornerRadius, leftBottomCornerRadius, rightBottomCornerRadius))
            } else {
                getDrawableLessThanLollipop(normalColor, cornerRadius, leftTopCornerRadius, rightTopCornerRadius,
                    leftBottomCornerRadius, rightBottomCornerRadius, dashWidth = dashWidth, dashGap = dashGap
                )
            }
        }

        private fun getDrawableLessThanLollipop(color: Int, cornerRadius: Float = 0f, leftTopCornerRadius: Float = 0f,
                                                rightTopCornerRadius: Float = 0f, leftBottomCornerRadius: Float = 0f,
                                                rightBottomCornerRadius: Float = 0f, strokeWidth: Int = 0,
                                                strokeColor: Int = Color.TRANSPARENT, dashWidth: Float,
                                                dashGap: Float
        ): Drawable {
            val shape = GradientDrawable()
            shape.setColor(color)
            if (cornerRadius > 0f) {
                shape.cornerRadius = cornerRadius
            } else {
                shape.cornerRadii = floatArrayOf(
                    leftTopCornerRadius, leftTopCornerRadius, rightTopCornerRadius, rightTopCornerRadius,
                    rightBottomCornerRadius, rightBottomCornerRadius, leftBottomCornerRadius, leftBottomCornerRadius
                )
            }
            shape.setStroke(strokeWidth, strokeColor, dashWidth, dashGap)
            return shape
        }

        private fun getStrokeDrawable(
            color: Int,
            cornerRadius: Float,
            leftTopCornerRadius: Float = 0f,
            rightTopCornerRadius: Float = 0f,
            leftBottomCornerRadius: Float = 0f,
            rightBottomCornerRadius: Float = 0f,
            strokeWidth: Int,
            strokeColor: Int,
            dashWidth: Float,
            dashGap: Float
        ): Drawable {
            val shapeDrawable = GradientDrawable()
            if (cornerRadius > 0f) {
                shapeDrawable.cornerRadius = cornerRadius
            } else {
                shapeDrawable.cornerRadii = floatArrayOf(
                    leftTopCornerRadius, leftTopCornerRadius, rightTopCornerRadius, rightTopCornerRadius,
                    rightBottomCornerRadius, rightBottomCornerRadius, leftBottomCornerRadius, leftBottomCornerRadius
                )
            }
            shapeDrawable.setColor(color)
            shapeDrawable.shape = GradientDrawable.RECTANGLE
            shapeDrawable.setStroke(strokeWidth, strokeColor, dashWidth, dashGap)
            return shapeDrawable
        }

        private fun getDrawable(color: Int, cornerRadius: Float = 0f, leftTopCornerRadius: Float = 0f, rightTopCornerRadius: Float = 0f,
                                leftBottomCornerRadius: Float = 0f, rightBottomCornerRadius: Float = 0f): Drawable {
            var r: RoundRectShape? = null
            val outerRadii: FloatArray?
            if (cornerRadius > 0f) {
                outerRadii = FloatArray(8)
                outerRadii.fill(cornerRadius, 0, 8)
                r = RoundRectShape(outerRadii, null, null)
            } else if (leftTopCornerRadius != 0f || rightTopCornerRadius != 0f ||
                leftBottomCornerRadius != 0f || rightBottomCornerRadius != 0f) {
                outerRadii = floatArrayOf(leftTopCornerRadius, leftTopCornerRadius, rightTopCornerRadius, rightTopCornerRadius,
                    rightBottomCornerRadius, rightBottomCornerRadius, leftBottomCornerRadius, leftBottomCornerRadius)
                r = RoundRectShape(outerRadii, null, null)
            }
            val shapeDrawable = ShapeDrawable(r)
            shapeDrawable.paint.color = color
            shapeDrawable.paint.isAntiAlias = true
            return shapeDrawable
        }

    }

}