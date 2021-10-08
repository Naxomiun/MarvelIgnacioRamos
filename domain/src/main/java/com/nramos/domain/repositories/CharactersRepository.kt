package com.nramos.domain.repositories

import com.nramos.domain.Either
import com.nramos.domain.model.Error
import com.nramos.domain.model.MarvelHero

interface CharactersRepository {

    suspend fun getCharacters() : Either<List<MarvelHero>, Error>

}