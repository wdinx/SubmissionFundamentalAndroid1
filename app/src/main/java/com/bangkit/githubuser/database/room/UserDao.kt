package com.bangkit.githubuser.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bangkit.githubuser.database.FavoriteUserRepository
import com.bangkit.githubuser.database.enitity.FavoriteUserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteUserEntity: FavoriteUserEntity)

//    @Query("SELECT * FROM favorite_user WHERE favorite_user.login LIKE :login")
//    suspend fun setBookmarkedUser(login: String)

    @Query("SELECT * FROM favorite_user WHERE login LIKE :login")
    fun getFavoriteByUser(login: String): LiveData<FavoriteUserEntity>

    @Query("DELETE FROM favorite_user WHERE login LIKE :login")
    suspend fun delete(login: String)
}