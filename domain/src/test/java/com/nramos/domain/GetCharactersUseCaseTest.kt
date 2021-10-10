package com.nramos.domain

import com.nramos.domain.model.ResponseError
import com.nramos.domain.repositories.CharactersRepository
import com.nramos.domain.usecases.GetCharacters
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    lateinit var useCaseToTest: GetCharacters

    @MockK lateinit var mockCharacterRepository: CharactersRepository

    @MockK lateinit var mockError: ResponseError

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCaseToTest = GetCharacters(mockCharacterRepository)
    }

    @Test
    fun `get characters and success`() {
        every { runBlocking { mockCharacterRepository.getCharacters() } } answers { Either.Success(listOf()) }
        runBlocking { assert(useCaseToTest.invoke() is Either.Success) }
    }

    @Test
    fun `get characters and fail`() {
        every { runBlocking { mockCharacterRepository.getCharacters() } } answers { Either.Failure(mockError) }
        runBlocking { assert(useCaseToTest.invoke() is Either.Failure) }
    }

}
