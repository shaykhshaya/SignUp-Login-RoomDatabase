package com.shaya.loginsignup.viewmodels


import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shaya.loginsignup.*
import com.shaya.loginsignup.data.Item
import com.shaya.loginsignup.data.ItemDao
import com.shaya.loginsignup.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val itemDao: ItemDao) : ViewModel() {

    fun checkUser(
        userName: String,
        password: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val userData: Item? = itemDao.getUsername(userName)
        showToastAsPerUser(userData, password)

    }

    private fun showToastAsPerUser(
        userData: Item?,
        password: String
    ) = viewModelScope.launch(Dispatchers.Main) {
        if (userData != null && userData.password == password) {
            Utils.storeUserLoggedIn(userData.userName)
            val context = BaseApplication.instance.applicationContext
            val intent = Intent(context, HomeScreenActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } else {
            Utils.showToast(if (userData != null) "Incorrect Password" else "User does not Exist")
        }
    }
}


class LoginViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}