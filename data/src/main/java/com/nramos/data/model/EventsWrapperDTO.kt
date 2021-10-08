package com.nramos.data.model

data class EventsWrapperDTO(
    val available: Int,
    val collectionURI: String,
    val items: List<EventWrapperDTO>,
    val returned: Int
)