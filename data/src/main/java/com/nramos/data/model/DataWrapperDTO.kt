package com.nramos.data.model

data class DataWrapperDTO(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<CharacterWrapperDTO>,
    val total: Int
)