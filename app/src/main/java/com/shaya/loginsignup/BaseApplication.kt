package com.shaya.loginsignup

import android.app.Application
import com.shaya.loginsignup.data.ItemRoomDatabase

class BaseApplication: Application() {

    companion object {
        lateinit var instance: BaseApplication
        lateinit var registrationDatabase: ItemRoomDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        registrationDatabase = ItemRoomDatabase.getDatabase(this)
    }
}