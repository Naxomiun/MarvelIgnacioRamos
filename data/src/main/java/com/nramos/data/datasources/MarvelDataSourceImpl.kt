package com.nramos.data.datasources

import com.nramos.data.PUBLIC_API_KEY
import com.nramos.data.services.MarvelService
import com.nramos.data.toDomain
import com.nramos.domain.Either
import com.nramos.domain.datasources.RemoteDatasource
import com.nramos.domain.eitherFailure
import com.nramos.domain.eitherSuccess
import com.nramos.domain.model.Error
import com.nramos.domain.model.MarvelHero
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val marvelService: MarvelService
) : RemoteDatasource {

    override suspend fun getMarvelCharacters(
        orderBy: String,
        thresholdSize: Int,
        limitSize: Int,
        timestamp: String,
        hash: String
    ): Either<List<MarvelHero>, Error> {

        val response = marvelService.getCharactersAsync(
            orderBy,
            thresholdSize,
            limitSize,
            timestamp,
            PUBLIC_API_KEY,
            hash
        )

        return if (response.isSuccessful)
            response.body()?.let {
                eitherSuccess(it.toDomain())
            } ?: eitherFailure(Error.Generic)
        else
            eitherFailure(Error.Network)
    }

    override suspend fun getMarvelCharacterById(id: String): Either<MarvelHero, Error> {

    }
}