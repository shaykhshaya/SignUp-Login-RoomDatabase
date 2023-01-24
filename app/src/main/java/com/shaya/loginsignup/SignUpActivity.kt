package com.shaya.loginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.shaya.loginsignup.databinding.ActivitySignUpBinding
import com.shaya.loginsignup.viewmodels.SignUpViewModel
import com.shaya.loginsignup.viewmodels.SignUpViewModelFactory

class SignUpActivity : AppCompatActivity() {

   private val viewModel: SignUpViewModel by viewModels{
       SignUpViewModelFactory((application as SignUpApplication).registrationDatabase.itemDao())
   }
    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {

            viewModel.addNewItem(
                binding.editTextFirstName.text.toString(),
                binding.editTextLastName.text.toString(),
                binding.editTextUsername.text.toString(),
                binding.editTextDob.text.toString(),
                binding.editTextGender.text.toString(),
                binding.editTextPassword.text.toString(),
                binding.editTextConfirmPassword.text.toString()
            )

            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}