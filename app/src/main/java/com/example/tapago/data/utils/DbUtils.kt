package com.example.tapago.data.utils

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import com.example.tapago.domain.wrapper.IResourceRoom

suspend fun <T> safeDbCall(dbCall: suspend () -> T): IResourceRoom<T>{

    return try {
        IResourceRoom.Success(dbCall())
    } catch (e: SQLiteConstraintException) {
        IResourceRoom.Error("Erro de restrição: Este registro já existe ou viola uma regra do banco.", e)
    } catch (e: SQLiteException) {
        IResourceRoom.Error("Erro no banco de dados.", e)
    } catch (e: Exception) {
        IResourceRoom.Error("Erro inesperado: ${e.localizedMessage}", e)
    }
}