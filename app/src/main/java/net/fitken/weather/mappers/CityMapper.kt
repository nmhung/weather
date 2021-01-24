package net.fitken.weather.mappers

import net.fitken.domain.entities.CityEntity
import net.fitken.weather.entities.City

object CityMapper {
    fun mapCityToPresentation(cityEntity: CityEntity): City {
        return City(cityEntity.name, cityEntity.country)
    }
}