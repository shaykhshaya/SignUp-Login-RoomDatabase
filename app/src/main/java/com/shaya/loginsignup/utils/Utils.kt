package com.shaya.loginsignup.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.shaya.loginsignup.BaseApplication
import com.shaya.loginsignup.IS_LOGGED_IN
import com.shaya.loginsignup.LOGGED_IN_USER
import java.io.File
import java.net.URI

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

    fun Uri.toGalleryImageFile(): File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = BaseApplication.instance.applicationContext.contentResolver?.query(this, filePathColumn, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val filePath = cursor.getString(columnIndex)
                cursor.close()
                return File(filePath)
            }
            cursor.close()
        }
        return null
    }

    fun ImageView.loadImageViaFile(file: File?){
        Glide
            .with(BaseApplication.instance.applicationContext)
            .load(file)
            .centerCrop()
            .dontAnimate()
            .into(this)

    }

}