package com.nramos.data.model

data class ResponseWrapperDTO(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: DataWrapperDTO,
    val etag: String,
    val status: String
)