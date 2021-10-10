package com.nramos.data.services

import com.nramos.data.CHARACTERS_ENDPOINT
import com.nramos.data.CHARACTER_DETAIL_ENDPOINT
import com.nramos.data.model.ResponseDetailWrapperDTO
import com.nramos.data.model.ResponseWrapperDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET(CHARACTERS_ENDPOINT)
    suspend fun getCharacters(
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<ResponseWrapperDTO>

    @GET(CHARACTER_DETAIL_ENDPOINT)
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<ResponseDetailWrapperDTO>

}