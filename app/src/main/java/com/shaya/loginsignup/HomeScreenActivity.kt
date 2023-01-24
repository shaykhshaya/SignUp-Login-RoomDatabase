package com.shaya.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shaya.loginsignup.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeScreenBinding

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
    }
}