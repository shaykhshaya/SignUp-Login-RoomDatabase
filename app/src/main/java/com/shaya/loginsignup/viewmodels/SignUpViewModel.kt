package com.shaya.loginsignup.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shaya.loginsignup.data.Item
import com.shaya.loginsignup.data.ItemDao
import kotlinx.coroutines.launch

class SignUpViewModel(private val itemDao: ItemDao): ViewModel() {


    fun addNewItem(
         firstName: String,
         lastName: String,
         userName: String,
         dob:String,
         gender: String,
         password:String,
         confirmPassword:String
    ){
        val newItem = Item(
            firstName = firstName,
            lastName = lastName,
            userName = userName,
            dob = dob,
            gender = gender,
            password = password,
            confirmPassword = confirmPassword
        )
        insertItem(newItem)
    }

    private fun insertItem(item: Item){
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }


}

class SignUpViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}