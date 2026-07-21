package com.example.tapago.presentation.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.repository.WorkoutRepositoryImp
import com.example.tapago.domain.wrapper.IResourceRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListSheetsViewModel(
    private var repo: WorkoutRepositoryImp
): ViewModel() {

    private var _uiState = MutableStateFlow(ListSheetsState())
    val uiState: StateFlow<ListSheetsState> = _uiState.asStateFlow()

    fun getSheet(){
        viewModelScope.launch {
            _uiState.update { it.copy(
                isLoanding = true
            ) }

            when(
                val result = repo.selectSheet()
            ){
                is IResourceRoom.Success -> {
                    _uiState.update { it.copy(
                        isLoanding = false,
                        sheets = result.data
                    ) }
                }
                is IResourceRoom.Error -> {
                    _uiState.update { it.copy(
                        isLoanding = false,
                    ) }
                }
            }
        }
    }
}