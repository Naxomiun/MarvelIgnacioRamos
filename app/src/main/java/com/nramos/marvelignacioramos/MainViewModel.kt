package com.nramos.marvelignacioramos


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.domain.onFailure
import com.nramos.domain.onSuccess
import com.nramos.domain.usecases.GetCharacters
import com.nramos.marvelignacioramos.ui.di.DefaultDispatcher
import com.nramos.marvelignacioramos.ui.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @IoDispatcher private val ioCoroutineDispatcher: CoroutineDispatcher, //Injecting dispatchers help to define and handle in what thread are we working
    private var getCharacters: GetCharacters
) : ViewModel() {

    private val _state = MutableStateFlow<State>(Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        /**
         * ViewModelScope is executed by default on Main.Inmediate dispatcher.
         * We shouldn't use this dispatcher for network or hard processes.
         *
         */
        viewModelScope.launch { //Start on Main.Inmediate
            val result = withContext(ioCoroutineDispatcher) { //Change to IODispatcher for Network call
                getCharacters()
            }
            //Return to Main.Inmediate to update our lists
            result.onSuccess {
                _state.value = Loaded(it)
            }.onFailure {
                _state.value = Error(it)
            }

        }
    }


}