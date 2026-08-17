package com.example.tapago.data.service

import com.example.tapago.data.repository.WorkoutRepositoryImp

private const val ONE_MINUTE = 60_000L
private const val ONE_HOUR = ONE_MINUTE * 60

class AppService(
    private var repo: WorkoutRepositoryImp
) {

    fun getNameSheet(): String{
        return repo.sheetInProgress!!.nameSheet
    }

    fun startTimer(){

    }
}