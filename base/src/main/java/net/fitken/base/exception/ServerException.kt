package net.fitken.base.exception

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ServerException(
    var message: String?, var cod: String
) {
    companion object {
        const val INVALID_API_KEY = 999
        const val FORBIDDEN = 403
    }
}