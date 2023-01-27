package com.shaya.loginsignup.data.friend

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friendTable")
data class FriendData(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "userName")
    val userName: String,
    @ColumnInfo(name = "path")
    val filePath:String
)
