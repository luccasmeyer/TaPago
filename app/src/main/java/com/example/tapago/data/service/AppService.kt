package com.example.tapago.data.service

import com.example.tapago.data.repository.WorkoutRepositoryImp

class AppService(
    private var repo: WorkoutRepositoryImp
) {

    fun getNameSheet(): String{
        return repo.sheetInProgress!!.nameSheet
    }
}