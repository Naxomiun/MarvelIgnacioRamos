package com.nramos.domain.datasources

import com.nramos.domain.Either
import com.nramos.domain.model.ResponseError
import com.nramos.domain.model.MarvelHero

interface RemoteDatasource {

    suspend fun getMarvelCharacters(
        orderBy: String = "",
        limit: Int,
        offset: Int,
        timestamp: String,
        hash: String
    ): Either<List<MarvelHero>, ResponseError>

    suspend fun getMarvelCharacterById(
        id: Int,
        timestamp: String,
        hash: String
    ): Either<MarvelHero, ResponseError>

}