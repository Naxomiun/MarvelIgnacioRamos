package com.nramos.domain.repositories

import com.nramos.domain.Either
import com.nramos.domain.model.ResponseError
import com.nramos.domain.model.MarvelHero

interface CharactersRepository {

    suspend fun getCharacters() : Either<List<MarvelHero>, ResponseError>

    suspend fun getCharacterDetail(id : Int) : Either<MarvelHero, ResponseError>

}