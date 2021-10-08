package com.nramos.data.di

import com.nramos.data.services.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataServicesProvider {

    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit) : MarvelService = retrofit.create(MarvelService::class.java)

}
