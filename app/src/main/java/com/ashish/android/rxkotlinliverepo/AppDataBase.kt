package com.ashish.android.rxkotlinliverepo

import androidx.room.*
import io.reactivex.Single

@Database(entities = arrayOf(User::class), version = 3)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
}

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    fun getUsers(): Single<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsers(users: List<User>)
}
