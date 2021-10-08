package com.nramos.data

import com.nramos.data.model.ResponseWrapperDTO
import com.nramos.domain.model.MarvelHero

fun ResponseWrapperDTO.toDomain(): List<MarvelHero>{
    return data.results.map {
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
}
