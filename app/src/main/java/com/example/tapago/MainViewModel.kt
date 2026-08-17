package com.example.tapago

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.repository.WorkoutRepositoryImp
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: WorkoutRepositoryImp
): ViewModel() {

    fun teste(){
        viewModelScope.launch {
            repo.getSheetProgress()
        }
    }
}