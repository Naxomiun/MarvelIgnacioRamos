package com.nramos.data.di

import com.nramos.data.repositories.CharactersRepositoryImpl
import com.nramos.domain.repositories.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataRepositoriesModule {

    @Binds
    internal abstract fun bindCharactersRepository(impl: CharactersRepositoryImpl): CharactersRepository

}