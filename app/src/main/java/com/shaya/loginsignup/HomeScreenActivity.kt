package com.shaya.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.shaya.loginsignup.databinding.ActivityHomeScreenBinding
import com.shaya.loginsignup.ui.HomeAdapter
import com.shaya.loginsignup.utils.Utils
import com.shaya.loginsignup.viewmodels.AddFriendViewModel
import com.shaya.loginsignup.viewmodels.FriendViewModelFactory

class HomeScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeScreenBinding
    private val adapter = HomeAdapter()

    private val viewModel : AddFriendViewModel by viewModels{
        FriendViewModelFactory(BaseApplication.registrationDatabase.friendDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addFab.setOnClickListener {
            startActivity(Intent(this,AddActivity::class.java))
        }
        binding.btnLogout.setOnClickListener {
            Utils.setUserLoggedOut()
            startActivity(Intent(this, OnboardingActivity::class.java))
        }

        binding.recyclerView.adapter = adapter


        viewModel.getFriendDao().getAllUsers(Utils.getLoggedInUser().orEmpty()).observe(this){
            adapter.setAuthors(it)
        }


    }


}