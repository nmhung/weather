package net.fitken.base.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerAdapter<VH : BaseRecyclerAdapter.BaseHolder<*, T>, T>(
        private var mOnItemClickListener: OnItemClickListener<T>? = null) : RecyclerView.Adapter<VH>() {

    protected val dataSource = ArrayList<T>()

    protected abstract fun getViewHolder(parent: ViewGroup, viewType: Int): VH?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val viewHolder = getViewHolder(parent, viewType)
        viewHolder?.let { vh ->
            vh.itemView.setOnClickListener {
                val pos = vh.adapterPosition
                if (pos != RecyclerView.NO_POSITION && dataSource[pos] != null) {
                    mOnItemClickListener?.onItemClick(it, pos, dataSource[pos]!!)
                }
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(dataSource[position])
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    open fun update(items: List<T>) {
        updateAllItems(items)
    }

    fun appendItems(items: List<T>?) {
        if (dataSource.isEmpty()) {
            updateAllItems(items!!)
        } else {
            if (items != null && items.isNotEmpty()) {
                val positionStart = dataSource.size
                dataSource.addAll(items)
                notifyItemRangeInserted(positionStart, items.size)
            }
        }
    }

    private fun updateAllItems(items: List<T>) {
        updateItemsInModel(items)
        notifyDataSetChanged()
    }

    private fun updateItemsInModel(items: List<T>) {
        dataSource.clear()
        dataSource.addAll(items)
    }

    protected open fun getData(): List<T> {
        return dataSource
    }

    open class BaseHolder<out V : ViewDataBinding, in T>(val mViewDataBinding: V) :
            RecyclerView.ViewHolder(mViewDataBinding.root) {

        open fun bindData(data: T) {}
    }

    interface OnItemClickListener<in T> {
        fun onItemClick(v: View, position: Int, data: T)
    }
}