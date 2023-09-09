package com.bangkit.githubuser.database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.jetbrains.annotations.NotNull

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteUser: FavoriteUser)

    @Query("SELECT * from favorite_user ORDER BY username")
    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>>

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun deleteAll(id: Int): Int

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun checkUserId(id: Int): Int
}