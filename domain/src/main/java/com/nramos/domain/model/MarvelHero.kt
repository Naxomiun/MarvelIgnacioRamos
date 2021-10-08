package com.nramos.domain.model

data class MarvelHero (
    val id: Int,
    val name: String,
    val thumbnail: String,
    val description: String,
    val comics: Int,
    val series: Int,
    val stories: Int,
    val events: Int
)