package net.fitken.weather.mappers

import net.fitken.domain.entities.TempEntity
import net.fitken.weather.entities.Temp


object TempMapper {
    fun mapTempToPresentation(tempEntity: TempEntity): Temp {
        return Temp(
            tempEntity.day,
            tempEntity.min,
            tempEntity.max,
            tempEntity.night,
            tempEntity.eve,
            tempEntity.morn
        )
    }
}