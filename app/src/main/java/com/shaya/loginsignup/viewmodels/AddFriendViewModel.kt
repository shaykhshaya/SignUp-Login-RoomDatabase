package com.shaya.loginsignup.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shaya.loginsignup.data.friend.FriendDao
import com.shaya.loginsignup.data.friend.FriendData
import kotlinx.coroutines.launch

class AddFriendViewModel(private val daoFriend: FriendDao): ViewModel() {


    fun addNewItem(
        firstName: String,
        lastName: String,
        authorUserName: String,
        filePath:String
    ){
        val newItem = FriendData(
            firstName = firstName,
            lastName = lastName,
            userName = authorUserName,
            filePath = filePath
        )
        insertItem(newItem)
    }

    private fun insertItem(item: FriendData){
        viewModelScope.launch {
            daoFriend.insert(item)
        }
    }

    fun getFriendDao() = daoFriend


}

class FriendViewModelFactory(private val itemDao: FriendDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddFriendViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddFriendViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
