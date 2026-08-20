package com.example.tapago

import com.example.tapago.data.repository.ExerciseRepositoryImp
import com.example.tapago.data.repository.ProfileRepositoryImp
import com.example.tapago.data.repository.WorkoutRepositoryImp
import com.example.tapago.data.service.AppService
import com.example.tapago.domain.model.Sheet
import com.example.tapago.presentation.menu.MenuViewModel
import com.example.tapago.presentation.profile.ProfileViewModel
import com.example.tapago.presentation.profile.RegisterProfileViewModel
import com.example.tapago.presentation.workout.RegisterSheetViewModel
import com.example.tapago.presentation.workout.ListSheetsViewModel
import com.example.tapago.presentation.workout.exercise.RegisterExerciseViewModel
import com.example.tapago.presentation.workout.exercise_sheet.ListExerciseSheetViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //Database
    single { AppDatabase.getDatabase(androidContext()) }
    single { get<AppDatabase>().ProfileDao() }
    single { get<AppDatabase>().SheetDao() }
    single { get<AppDatabase>().ExerciseDao() }
    single { get<AppDatabase>().ExerciseSheetDao() }
    single { get<AppDatabase>().SetExerciseSheetDao() }

    //Repository
    single { ProfileRepositoryImp(get()) }
    single { ExerciseRepositoryImp(get()) }
    single { WorkoutRepositoryImp(get(), get(), get(), get()) }

    //Service
    single { AppService(get()) }

    //Viewmodel
    viewModel { MenuViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { RegisterProfileViewModel(get()) }
    viewModel { ListSheetsViewModel(get()) }
    viewModel { RegisterSheetViewModel(get(), get()) }
    viewModel { RegisterExerciseViewModel(get()) }
    viewModel { ListExerciseSheetViewModel(get()) }
    viewModel { MainViewModel(get()) }
}