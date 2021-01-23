package net.fitken.base.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION

abstract class BasePagedListAdapter<VH : BasePagedListAdapter.BaseHolder<*, T>, T>(
        comparator: BaseComparator<T>,
        private var mOnItemClickListener: OnItemClickListener<T>? = null
) : PagedListAdapter<T, VH>(comparator) {


    protected abstract fun getViewHolder(parent: ViewGroup, viewType: Int): VH?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val viewHolder = getViewHolder(parent, viewType)
        viewHolder?.let { vh ->
            vh.itemView.setOnClickListener {
                val pos = vh.adapterPosition
                if (pos != NO_POSITION && getItem(pos) != null) {
                    mOnItemClickListener?.onItemClick(it, pos, getItem(pos)!!)
                }
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }

    open class BaseHolder<out V : ViewDataBinding, in T>(val mViewDataBinding: V) :
            RecyclerView.ViewHolder(mViewDataBinding.root) {

        open fun bindData(data: T) {}
    }

    interface OnItemClickListener<in T> {
        fun onItemClick(v: View, position: Int, data: T)
    }
}