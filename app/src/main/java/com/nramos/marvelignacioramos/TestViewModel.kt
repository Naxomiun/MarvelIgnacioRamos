package com.nramos.marvelignacioramos


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.domain.onFailure
import com.nramos.domain.onSuccess
import com.nramos.domain.usecases.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private var getCharacters: GetCharacters
) : ViewModel() {

    init {
        viewModelScope.launch {
            getCharacters().onSuccess {
                it
            }.onFailure {
                it
            }
        }
    }


}