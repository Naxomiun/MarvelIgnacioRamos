package com.nramos.domain.datasources

import com.nramos.domain.Either
import com.nramos.domain.model.ResponseError
import com.nramos.domain.model.MarvelHero

interface RemoteDatasource {

    suspend fun getMarvelCharacters(orderBy : String = "", thresholdSize : Int, limitSize : Int, timestamp : String, hash : String) :  Either<List<MarvelHero>, ResponseError>

    suspend fun getMarvelCharacterById(id : String) :  Either<MarvelHero, ResponseError>

}