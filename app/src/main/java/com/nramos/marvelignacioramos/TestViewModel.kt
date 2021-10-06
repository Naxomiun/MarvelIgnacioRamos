package com.nramos.marvelignacioramos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.marvelignacioramos.domain.usecases.GetCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TestViewModel @Inject constructor(
    private var getCharacters: GetCharacters
) : ViewModel() {

    init {
        viewModelScope.launch {
            getCharacters()
        }
    }


}