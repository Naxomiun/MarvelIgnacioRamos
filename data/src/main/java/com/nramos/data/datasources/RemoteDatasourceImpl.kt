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
        limit: Int,
        offset: Int,
        timestamp: String,
        hash: String
    ): Either<List<MarvelHero>, ResponseError> {
        return try {
            val response = marvelService.getCharacters(
                orderBy,
                limit,
                offset,
                timestamp,
                PUBLIC_API_KEY,
                hash
            )
            if (response.isSuccessful)
                response.body()?.let {
                    when (it.code) {
                        200 -> eitherSuccess(it.toDomain()) //TODO futurible: handle every status code and replace it with an enum and handler
                        //404 -> eitherFailure(ResponseError.NotFound)
                        //500 -> eitherFailure(ResponseError.InternalServerError)
                        else -> eitherFailure(ResponseError.Generic(it.status))
                    }
                } ?: eitherFailure(ResponseError.Generic("Error: empty response"))
            else eitherFailure(ResponseError.Generic("Error: Not successful")) //TODO futurible: replace by deserializing it.ResponseBody() to get detailed info
        } catch (exception: Exception) {
            eitherFailure(ResponseError.Network)
        }
    }

    override suspend fun getMarvelCharacterById(
        id: Int,
        timestamp: String,
        hash: String
    ): Either<MarvelHero, ResponseError> {
        return try {
            val response = marvelService.getCharacterDetail(
                id,
                timestamp,
                PUBLIC_API_KEY,
                hash
            )

            if (response.isSuccessful)
                response.body()?.let {
                    when (it.code) {
                        200 -> eitherSuccess(it.toDomain())
                        else -> eitherFailure(ResponseError.Generic(it.status))
                    }
                } ?: eitherFailure(ResponseError.Generic("Error: empty response"))
            else eitherFailure(ResponseError.Generic("Error: Not successful"))
        } catch (exception: Exception) {
            eitherFailure(ResponseError.Network)
        }
    }
}