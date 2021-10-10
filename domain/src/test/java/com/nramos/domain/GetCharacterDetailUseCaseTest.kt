package com.nramos.domain

import com.nramos.domain.model.MarvelHero
import com.nramos.domain.model.ResponseError
import com.nramos.domain.repositories.CharactersRepository
import com.nramos.domain.usecases.GetCharacterDetail
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharacterDetailUseCaseTest {

    lateinit var useCaseToTest: GetCharacterDetail

    @MockK lateinit var mockCharacterRepository: CharactersRepository

    @MockK lateinit var mockError: ResponseError

    @MockK lateinit var mockCharacter: MarvelHero

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCaseToTest = GetCharacterDetail(mockCharacterRepository)
    }

    @Test
    fun `get character detail and success`() {
        every { runBlocking { mockCharacterRepository.getCharacterDetail(0) } } answers { Either.Success(mockCharacter) }
        runBlocking { assert(useCaseToTest.invoke(0) is Either.Success) }
    }

    @Test
    fun `get character detail and fail`() {
        every { runBlocking { mockCharacterRepository.getCharacterDetail(0) } } answers { Either.Failure(mockError) }
        runBlocking { assert(useCaseToTest.invoke(0) is Either.Failure) }
    }

}
