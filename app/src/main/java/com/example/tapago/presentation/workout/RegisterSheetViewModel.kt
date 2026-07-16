package com.example.tapago.presentation.workout

import androidx.lifecycle.ViewModel
import com.example.tapago.data.repository.SheetRepositoryImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterSheetViewModel(
    private var repo: SheetRepositoryImp
) : ViewModel() {

    private var _uiState = MutableStateFlow(RegisterSheetState())
    val uiState: StateFlow<RegisterSheetState> = _uiState.asStateFlow()


}