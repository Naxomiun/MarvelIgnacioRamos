package com.nramos.domain.usecases

import com.nramos.domain.*
import com.nramos.domain.model.ResponseError
import com.nramos.domain.model.MarvelHero
import com.nramos.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacterDetail @Inject constructor(
    private val charactersRepository: CharactersRepository
)  {

    suspend operator fun invoke(id : Int) : Either<MarvelHero, ResponseError> =
        charactersRepository.getCharacterDetail(id).onSuccess {
            return eitherSuccess(it)
        }.onFailure {
            return eitherFailure(it)
        }

}