package com.nramos.data.model

data class CharacterWrapperDTO(
    val comics: ComicsWrapperDTO,
    val description: String,
    val events: EventsWrapperDTO,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: SeriesWrapperDTO,
    val stories: StoriesWrapperDTO,
    val thumbnail: ThumbnailWrapperDTO,
    val urls: List<UrlWrapperDTO>
)