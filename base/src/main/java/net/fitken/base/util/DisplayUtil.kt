package net.fitken.base.util

import android.content.Context
import android.util.TypedValue

/**
 * Created by vophamtuananh on 12/24/17.
 */
class DisplayUtil {

    companion object {
        private var deviceWidth = 0
        private var deviceHeight = 0

        fun getDeviceWidth(context: Context): Int {
            if (deviceWidth == 0)
                deviceWidth = context.resources.displayMetrics.widthPixels
            return deviceWidth
        }

        fun getDeviceHeight(context: Context): Int {
            if (deviceHeight == 0)
                deviceHeight = context.resources.displayMetrics.heightPixels
            return deviceHeight
        }

        fun convertDpToPx(context: Context?, dp: Float): Float {
            if (context == null) return 0F
            val r = context.resources
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dp, r.displayMetrics)
        }

        fun convertSpToPx(context: Context?, sp: Float): Float {
            if (context == null) {
                return 0F
            }
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics)
        }
    }
}