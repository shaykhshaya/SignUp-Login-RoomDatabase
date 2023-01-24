package com.shaya.loginsignup

import android.app.Application
import com.shaya.loginsignup.data.ItemRoomDatabase

class SignUpApplication: Application() {
    val registrationDatabase: ItemRoomDatabase by lazy {
        ItemRoomDatabase.getDatabase(this)
    }
}