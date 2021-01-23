package net.fitken.base.widget

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.WindowInsets
import android.widget.FrameLayout

/**
 * A FrameLayout subclass that dispatches WindowInsets to its children instead of adjusting its padding.
 * Useful for Fragment containers.
 *
 * @author Pkmmte Xeleon
 */
class WindowInsetsFrameLayout : FrameLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        setPadding(0, 0, 0, insets.systemWindowInsetBottom)
        val childCount = childCount
        var consumed = false
        for (index in 0 until childCount) {
            val childResult = getChildAt(index).dispatchApplyWindowInsets(insets)
            // If the child consumed the insets, record it
            if (childResult.isConsumed) {
                consumed = true
            }
        }
        // If any of the children consumed the insets, return
        // an appropriate value
        return if (consumed) insets.consumeSystemWindowInsets() else insets
    }
}