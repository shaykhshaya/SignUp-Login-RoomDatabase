package com.shaya.loginsignup.data.friend

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Query("SELECT * from signUpList ORDER BY id DESC")
    fun getAllUsers(): LiveData<List<Item>>

    @Query("SELECT * from signUpList WHERE userName = :userName")
    fun getUsername(userName: String): Item?
}