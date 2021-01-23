package net.fitken.base.recyclerview

import androidx.recyclerview.widget.DiffUtil

abstract class BaseComparator<T> : DiffUtil.ItemCallback<T>() {
    abstract override fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract override fun areContentsTheSame(oldItem: T, newItem: T): Boolean
}