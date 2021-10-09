package com.nramos.domain.model

sealed interface ResponseError  {
    data class Generic(val message : CharSequence) : ResponseError
    object Network : ResponseError
}
