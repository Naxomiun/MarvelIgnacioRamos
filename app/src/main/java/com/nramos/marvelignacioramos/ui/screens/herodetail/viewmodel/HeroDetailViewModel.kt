package com.nramos.marvelignacioramos.ui.screens.herodetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.domain.onFailure
import com.nramos.domain.onSuccess
import com.nramos.domain.usecases.GetCharacterDetail
import com.nramos.marvelignacioramos.ui.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    @IoDispatcher private val ioCoroutineDispatcher: CoroutineDispatcher,
    private var getCharacterDetail: GetCharacterDetail
) : ViewModel() {

    private var heroId : Int = 0 //We save hero ID here just in case our fragment gets detached. We could have injected it with assistedInjection

    private val _state = MutableStateFlow<State>(Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    fun getCharacterData(heroId : Int) {
        if(heroId != 0)
            this.heroId = heroId

        viewModelScope.launch {
            val result = withContext(ioCoroutineDispatcher) {
                getCharacterDetail(this@HeroDetailViewModel.heroId)
            }
            result.onSuccess {
                _state.value = Loaded(it)
            }.onFailure {
                _state.value = Error(it)
            }
        }

    }

}