package com.navgde.droidconsf.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author Nav Singh
 */
class MainActivityViewModel : ViewModel() {
    private var _backCallback: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val backCallback: StateFlow<Boolean> get() = _backCallback

    fun updateFlag(shouldUnregister: Boolean) {
        viewModelScope.launch {
            _backCallback.emit(shouldUnregister)
        }
    }

}