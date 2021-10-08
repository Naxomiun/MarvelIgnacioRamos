package com.nramos.data.model

data class StoriesWrapperDTO(
    val available: Int,
    val collectionURI: String,
    val items: List<StoryWrapperDTO>,
    val returned: Int
)