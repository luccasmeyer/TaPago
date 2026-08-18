package com.example.tapago.presentation.menu

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.repository.WorkoutRepositoryImp
import com.example.tapago.domain.wrapper.IResourceRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuViewModel(
    private val repo: WorkoutRepositoryImp
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O) // PROVISORIO
    val dateOfWeek = java.time.LocalDate.now().dayOfWeek

    private var _uiState = MutableStateFlow(MenuState())
    val uiState: StateFlow<MenuState> = _uiState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWorkout() {
        viewModelScope.launch {
            when (val result = repo.getWorkoutDay(dateOfWeek.toString().lowercase())) {
                is IResourceRoom.Error -> {
                    _uiState.update {
                        it.copy(
                            isError = true,
                            message = "${result.message}: ${result.exception}"
                        )
                    }
                }

                is IResourceRoom.Success -> {
                    _uiState.update {
                        it.copy(
                            sheetDay = result.data
                        )
                    }
                }
            }
        }
    }
}