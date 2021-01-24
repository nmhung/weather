package net.fitken.domain.common

interface Interactor {
    fun loading(isLoading: Boolean)
    fun error(exception: Exception?)
}