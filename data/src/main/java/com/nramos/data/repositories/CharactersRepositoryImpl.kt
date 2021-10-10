package com.nramos.data.repositories

import com.nramos.data.PRIVATE_API_KEY
import com.nramos.data.PUBLIC_API_KEY
import com.nramos.data.md5Hash
import com.nramos.domain.Either
import com.nramos.domain.datasources.RemoteDatasource
import com.nramos.domain.model.ResponseError
import com.nramos.domain.model.MarvelHero
import com.nramos.domain.repositories.CharactersRepository
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDatasource
    //TODO futurible: create and inject localDatasource to load cached results if any
) : CharactersRepository {

    companion object{
        const val LIMIT = 100 // API limit of 100
        const val OFFSET = 0 //
    }

    //TODO futurible: move this
    private val ts = System.currentTimeMillis()
    private val hash = (ts.toString() + PRIVATE_API_KEY + PUBLIC_API_KEY).md5Hash()

    override suspend fun getCharacters(): Either<List<MarvelHero>, ResponseError> {
        //TODO futurible: check internet connection/cached results with localDatasource
        return remoteDatasource.getMarvelCharacters(
            "name",
            LIMIT,
            OFFSET,
            ts.toString(),
            hash
        )
    }

    override suspend fun getCharacterDetail(id : Int) : Either<MarvelHero, ResponseError> {
        return remoteDatasource.getMarvelCharacterById(id, ts.toString(), hash)
    }
}