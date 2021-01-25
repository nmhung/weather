package net.fitken.weather

import android.text.format.DateFormat
import io.mockk.mockkStatic
import net.fitken.weather.utils.timestampToDate
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Mockito.mock

class ExtensionsUnitTest {

    @Test
    fun formatDateFromTimestamp_isCorrect() {

        val dateFormat: DateFormat = mock(DateFormat::class.java)
        val timestamp: Long = 1611464400
        val result = timestamp.timestampToDate()
        assertThat(result, `is`(equalTo("Sun, 24 Jan 2021")))
    }
}