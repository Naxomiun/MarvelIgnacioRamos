package com.nramos.marvelignacioramos

import com.nramos.domain.model.MarvelHero
import com.nramos.domain.model.ResponseError

sealed interface State

data class Loaded(val heroes : List<MarvelHero>) : State

object Loading : State

data class Error(val error : ResponseError) : State