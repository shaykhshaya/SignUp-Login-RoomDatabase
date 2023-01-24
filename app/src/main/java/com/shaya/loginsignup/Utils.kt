package com.shaya.loginsignup

import android.content.Context
import android.service.autofill.UserData
import android.widget.Toast
import com.shaya.loginsignup.data.Item

object Utils {

    fun showToast(message: String) {
        Toast.makeText(BaseApplication.instance.applicationContext, message, Toast.LENGTH_SHORT)
            .show()
    }

    fun storeUserLoggedIn(userName: String) {
        val sharedPreference = BaseApplication.instance.applicationContext.getSharedPreferences(
            "PREFERENCE_NAME",
            Context.MODE_PRIVATE
        )
        val editor = sharedPreference.edit()
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.putString(LOGGED_IN_USER, userName)
        editor.commit()
    }

    fun isUserLoggedIn(): Boolean {
        val sharedPreference = BaseApplication.instance.applicationContext.getSharedPreferences(
            "PREFERENCE_NAME",
            Context.MODE_PRIVATE
        )
        return sharedPreference.getBoolean(IS_LOGGED_IN, false)
    }


    fun getLoggedInUser(): String? {
        val sharedPreference = BaseApplication.instance.applicationContext.getSharedPreferences(
            "PREFERENCE_NAME",
            Context.MODE_PRIVATE
        )
        return sharedPreference.getString(LOGGED_IN_USER, "")
    }

    fun setUserLoggedOut(){
        val sharedPreference = BaseApplication.instance.applicationContext.getSharedPreferences(
            "PREFERENCE_NAME",
            Context.MODE_PRIVATE
        )
        val editor = sharedPreference.edit()
        editor.putBoolean(IS_LOGGED_IN, false)
        editor.putString(LOGGED_IN_USER, "")
        editor.commit()
    }
}