package com.shaya.loginsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.shaya.loginsignup.databinding.ActivityLoginBinding
import com.shaya.loginsignup.viewmodels.LoginViewModel
import com.shaya.loginsignup.viewmodels.LoginViewModelFactory
import com.shaya.loginsignup.viewmodels.SignUpViewModel
import com.shaya.loginsignup.viewmodels.SignUpViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel : LoginViewModel by viewModels{
        LoginViewModelFactory(BaseApplication.registrationDatabase.itemDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            viewModel.checkUser(
                binding.editTextUsername.text.toString(),
                binding.editTextPassword.text.toString()
            )



        }
    }
}