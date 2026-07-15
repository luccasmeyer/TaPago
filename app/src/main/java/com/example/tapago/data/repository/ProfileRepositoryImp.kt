package com.example.tapago.data.repository

import com.example.tapago.data.daos.ProfileDao
import com.example.tapago.data.entities.ProfileEntity
import com.example.tapago.data.mapper.toDomain
import com.example.tapago.data.utils.safeDbCall
import com.example.tapago.domain.common.map
import com.example.tapago.domain.model.Profile
import com.example.tapago.domain.repository.ITaPagoRepository
import com.example.tapago.domain.wrapper.IResourceRoom

class ProfileRepositoryImp(
    private val dao: ProfileDao,
    private var profileCache: Profile? = null,
    private var getDatabaseYet: Boolean = false
): ITaPagoRepository<ProfileEntity> {

    suspend fun getProfile(): IResourceRoom<Profile> {

        if(getDatabaseYet){
            return IResourceRoom.Success(profileCache!!)
        }

        return safeDbCall { dao.getProfile() }.map { entity ->
            val profile = entity.toDomain()

            profileCache = profile
            getDatabaseYet = true

            profile
        }
    }

    override suspend fun select(): IResourceRoom<List<ProfileEntity>> = safeDbCall {
        TODO("Not yet implemented")
    }

    override suspend fun insert(item: ProfileEntity): IResourceRoom<Unit> = safeDbCall{
        dao.insertProfile(item)
    }

    override suspend fun update(item: ProfileEntity): IResourceRoom<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(item: ProfileEntity): IResourceRoom<Unit> {
        TODO("Not yet implemented")
    }
}