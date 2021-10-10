package com.nramos.marvelignacioramos.ui.screens.herodetail.viewmodel

import com.nramos.domain.model.MarvelHero
import com.nramos.domain.model.ResponseError

sealed interface State

data class Loaded(val hero : MarvelHero) : State

object Loading : State

data class Error(val error : ResponseError) : State