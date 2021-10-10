package com.nramos.marvelignacioramos.ui.screens.herolist.viewmodel

sealed interface Event

data class NavigateToDetail(val heroId : Int) : Event