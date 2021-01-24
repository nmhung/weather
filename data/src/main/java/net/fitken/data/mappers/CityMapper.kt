package net.fitken.data.mappers

import net.fitken.data.entities.CityData
import net.fitken.domain.entities.CityEntity

object CityMapper {
    fun mapCityToDomain(cityData: CityData): CityEntity {
        return CityEntity(cityData.name,cityData.country)
    }
}