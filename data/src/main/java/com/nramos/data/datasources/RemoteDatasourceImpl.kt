package com.nramos.data.datasources

import com.nramos.data.PUBLIC_API_KEY
import com.nramos.data.services.MarvelService
import com.nramos.data.toDomain
import com.nramos.domain.Either
import com.nramos.domain.datasources.RemoteDatasource
import com.nramos.domain.eitherFailure
import com.nramos.domain.eitherSuccess
import com.nramos.domain.model.ResponseError
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
    ): Either<List<MarvelHero>, ResponseError> {
        return try {
            val response = marvelService.getCharacters(
                orderBy,
                thresholdSize,
                limitSize,
                timestamp,
                PUBLIC_API_KEY,
                hash
            )
            if (response.isSuccessful)
                response.body()?.let {
                    eitherSuccess(it.toDomain())
                } ?: eitherFailure(ResponseError.Generic("Error: empty response"))
            else
                eitherFailure(ResponseError.Generic(response.message()))
        } catch (exception : Exception) {
            eitherFailure(ResponseError.Network)
        }
    }

    override suspend fun getMarvelCharacterById(id: String): Either<MarvelHero, ResponseError> {
        return eitherFailure(ResponseError.Generic("sad"))
    }
}