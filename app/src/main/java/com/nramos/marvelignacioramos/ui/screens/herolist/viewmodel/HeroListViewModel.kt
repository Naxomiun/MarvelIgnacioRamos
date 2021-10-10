package com.nramos.marvelignacioramos.ui.screens.herolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.domain.onFailure
import com.nramos.domain.onSuccess
import com.nramos.domain.usecases.GetCharacters
import com.nramos.marvelignacioramos.ui.di.IoDispatcher
import com.nramos.marvelignacioramos.ui.screens.herolist.viewmodel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    @IoDispatcher private val ioCoroutineDispatcher: CoroutineDispatcher, //Injecting dispatchers help to define and handle in what thread are we working.
    private var getCharacters: GetCharacters
) : ViewModel() {

    //States control persistent changes in UI
    private val _state = MutableStateFlow<State>(Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    //Events control volatile changes in UI (toasts, snackbars, navigation, dialogs etc.).
    //Channels ensure that events are not called twice in case of config changes or activity recreation.
    private val _event = Channel<Event>()
    val event: Flow<Event> = _event.receiveAsFlow()

    init {
        /*
         * ViewModelScope is executed by default on Main.Inmediate dispatcher.
         * We shouldn't use this dispatcher for network or hard processes.
         */
        viewModelScope.launch { //Start on Main.Inmediate
            val result = withContext(ioCoroutineDispatcher) { //Change to IO Dispatcher for Network call
                getCharacters()
            }
            //Return to Main.Inmediate to update our UI
            result.onSuccess {
                _state.value = Loaded(it)
            }.onFailure {
                _state.value = Error(it)
            }

        }
    }

    fun navigateToDetail(heroId : Int) {
        viewModelScope.launch {
            _event.send(NavigateToDetail(heroId))
        }
    }

}