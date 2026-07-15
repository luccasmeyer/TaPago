package com.example.tapago.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.entities.ProfileEntity
import com.example.tapago.data.repository.ProfileRepositoryImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterProfileViewModel(
    private var repo: ProfileRepositoryImp,
): ViewModel() {

    private var _uiSttate = MutableStateFlow(RegisterProfileState())
    val uistate: StateFlow<RegisterProfileState> = _uiSttate.asStateFlow()

    fun postProfile(item: ProfileEntity){
        viewModelScope.launch {
            repo.insert(item)
        }
    }
}