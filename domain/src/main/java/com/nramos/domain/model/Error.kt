package com.nramos.domain.model

sealed interface Error  {
    object Generic : Error
    object Network : Error
}
