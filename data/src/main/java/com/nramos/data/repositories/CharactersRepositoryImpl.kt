package com.nramos.data.repositories

import com.nramos.data.PRIVATE_API_KEY
import com.nramos.data.PUBLIC_API_KEY
import com.nramos.data.services.MarvelService
import com.nramos.data.toDomain
import com.nramos.domain.Either
import com.nramos.domain.eitherFailure
import com.nramos.domain.eitherSuccess
import com.nramos.domain.model.Error
import com.nramos.domain.model.MarvelHero
import com.nramos.domain.repositories.CharactersRepository
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    val marvelService: MarvelService
) : CharactersRepository {

    companion object{
        const val NETWORK_LIMIT_CHARACTERS = 100 // API limit of 100
        const val THRESHOLD_SIZE = 25
    }

    private val ts = System.currentTimeMillis()
    private val hash = (ts.toString() + PRIVATE_API_KEY + PUBLIC_API_KEY).md5Hash()

    fun String.md5Hash(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    override suspend fun getCharacters() : Either<List<MarvelHero>, Error> {
        val response = marvelService.getCharactersAsync(
            "",
            THRESHOLD_SIZE,
            NETWORK_LIMIT_CHARACTERS,
            ts.toString(),
            PUBLIC_API_KEY,
            hash)

        return if(response.isSuccessful)
            eitherSuccess(response.body()?.toDomain() ?: emptyList())
        else
            eitherFailure(Error.Generic)
    }
}