package com.nramos.marvelignacioramos.ui.di

import com.nramos.domain.repositories.CharactersRepository
import com.nramos.domain.usecases.GetCharacterDetail
import com.nramos.domain.usecases.GetCharacters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun getCharactersUseCaseProvider(charactersRepository: CharactersRepository) = GetCharacters(charactersRepository)

    @Provides
    fun getCharacterDetailUseCaseProvider(charactersRepository: CharactersRepository) = GetCharacterDetail(charactersRepository)

}