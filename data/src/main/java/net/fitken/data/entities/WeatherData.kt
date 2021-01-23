package net.fitken.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "weather")
data class WeatherData(

        @PrimaryKey
        var id: Int = 0,

        @Json(name = "original_title")
        var originalTitle: String? = null,

        ) : Parcelable