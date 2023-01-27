package com.shaya.loginsignup.data.friend

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FriendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(friendData: FriendData)

    @Query("SELECT * FROM friendTable WHERE username = :userName")
    fun getAllUsers(userName: String): LiveData<List<FriendData>>

}