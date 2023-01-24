package com.shaya.loginsignup.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "signUpList")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "userName")
    val userName: String,
    @ColumnInfo(name = "dob")
    val dob:String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "password")
    val password:String,
    @ColumnInfo(name = "confirmPassword")
    val confirmPassword:String
)
