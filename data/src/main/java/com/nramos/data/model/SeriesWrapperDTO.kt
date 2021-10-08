package com.nramos.data.model

data class SeriesWrapperDTO(
    val available: Int,
    val collectionURI: String,
    val items: List<SerieWrapperDTO>,
    val returned: Int
)