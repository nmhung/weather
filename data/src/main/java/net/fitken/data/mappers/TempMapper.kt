package net.fitken.data.mappers

import net.fitken.data.entities.TempData

object TempMapper {
    fun mapTempToDomain(tempData: TempData): net.fitken.domain.entities.TempEntity {
        return net.fitken.domain.entities.TempEntity(
            tempData.day,
            tempData.min,
            tempData.max,
            tempData.night,
            tempData.eve,
            tempData.morn
        )
    }
}