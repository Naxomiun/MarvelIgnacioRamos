package com.nramos.data.model

data class ComicsWrapperDTO(
    val available: Int,
    val collectionURI: String,
    val items: List<ComicWrapperDTO>,
    val returned: Int
)