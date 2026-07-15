package com.example.tapago

import com.example.tapago.data.daos.ProfileDao
import com.example.tapago.data.repository.ProfileRepositoryImp
import com.example.tapago.presentation.menu.MenuViewModel
import com.example.tapago.presentation.profile.ProfileViewModel
import com.example.tapago.presentation.profile.RegisterProfileViewModel
import com.example.tapago.presentation.workout.WorkoutViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(androidContext()) }
    single { ProfileRepositoryImp(get()) }
    single { get<AppDatabase>().ProfileDao() }

    viewModel { MenuViewModel() }
    viewModel { ProfileViewModel(get()) }
    viewModel { RegisterProfileViewModel() }
    viewModel { WorkoutViewModel() }
}