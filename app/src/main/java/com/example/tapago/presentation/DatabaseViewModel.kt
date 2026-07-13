package com.example.tapago.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.daos.ExerciseDao
import kotlinx.coroutines.launch

class DatabaseViewModel(
    private val myDao: ExerciseDao
): ViewModel() {

    fun forcarCriacaoDoBanco() {
        viewModelScope.launch {
            myDao.findAll()
        }
    }
}