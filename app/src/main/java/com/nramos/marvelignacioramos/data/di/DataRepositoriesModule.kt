package com.nramos.marvelignacioramos.data.di

import com.nramos.marvelignacioramos.data.repositories.CharactersRepositoryImpl
import com.nramos.marvelignacioramos.domain.repositories.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataRepositoriesModule {

    @Binds
    internal abstract fun bindCharactersRepository(impl: CharactersRepositoryImpl): CharactersRepository

}