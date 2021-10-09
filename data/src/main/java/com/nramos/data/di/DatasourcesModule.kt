package com.nramos.data.di

import com.nramos.data.datasources.RemoteDatasourceImpl
import com.nramos.domain.datasources.RemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DatasourcesModule {

    @Binds
    internal abstract fun bindRemoteDatasource(impl: RemoteDatasourceImpl): RemoteDatasource

}