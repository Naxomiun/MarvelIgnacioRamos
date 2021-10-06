package com.nramos.marvelignacioramos.data.services

import retrofit2.http.GET


interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(): String



}