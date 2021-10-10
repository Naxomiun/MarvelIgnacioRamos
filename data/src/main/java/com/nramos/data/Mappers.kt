package com.nramos.data

import com.nramos.data.model.ResponseDetailWrapperDTO
import com.nramos.data.model.ResponseWrapperDTO
import com.nramos.domain.model.MarvelHero

fun ResponseWrapperDTO.toDomain(): List<MarvelHero> =
    data.results.map {
        MarvelHero(
            id = it.id,
            name = it.name,
            thumbnail = "${it.thumbnail.path}.${it.thumbnail.extension}",
            description = it.description,
            comics = it.comics.available,
            series = it.series.available,
            stories = it.stories.available,
            events = it.events.available,
        )
    }


fun ResponseDetailWrapperDTO.toDomain(): MarvelHero {
    val heroData = data.results.first()
    val description = if(heroData.description.isEmpty()) "Description not available" else heroData.description

    return MarvelHero(
        id = heroData.id,
        name = heroData.name,
        thumbnail = "${heroData.thumbnail.path}.${heroData.thumbnail.extension}",
        description = description,
        comics = heroData.comics.available,
        series = heroData.series.available,
        stories = heroData.stories.available,
        events = heroData.events.available,
    )
}


