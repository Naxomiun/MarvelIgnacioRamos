package com.nramos.data.model

data class DataDetailWrapperDTO(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: CharacterWrapperDTO,
    val total: Int
)