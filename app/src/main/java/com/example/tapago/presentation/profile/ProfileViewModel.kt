package com.example.tapago.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.entities.ProfileEntity
import com.example.tapago.data.repository.ProfileRepositoryImp
import com.example.tapago.domain.model.Profile
import com.example.tapago.domain.wrapper.IResourceRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repo: ProfileRepositoryImp
): ViewModel() {
    private var _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState> = _uiState.asStateFlow()

    fun getProfile(){
        viewModelScope.launch {
            _uiState.update { it.copy(
                isLoading = true
            ) }

            when(
                val result = repo.getProfile()
            ){
                is IResourceRoom.Success -> {
                    _uiState.update { it.copy(
                        isLoading = false,
                        profile = result.data
                    ) }
                }
                is IResourceRoom.Error -> {
                    _uiState.update { it.copy(
                        isLoading = false,
                        message = result.message
                    ) }
                }
            }
        }
    }
}