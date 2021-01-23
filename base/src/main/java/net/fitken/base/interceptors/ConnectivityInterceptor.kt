package net.fitken.base.interceptors

import android.content.Context
import net.fitken.base.exception.NoInternetException
import net.fitken.base.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val mContext: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isInternetAvailable(mContext)) {
            throw NoInternetException("No internet connection. Please check your connection and try again.")
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}