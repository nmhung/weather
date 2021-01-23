package net.fitken.base.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(
        space: Int
) : RecyclerView.ItemDecoration() {

    private val mSpace = space / 2

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        if (parent.paddingLeft != mSpace) {
            parent.setPadding(mSpace, mSpace, mSpace, mSpace)
            parent.clipToPadding = false
        }

        outRect.top = mSpace
        outRect.bottom = mSpace
        outRect.left = mSpace
        outRect.right = mSpace

    }
}