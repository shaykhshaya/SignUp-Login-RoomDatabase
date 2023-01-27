package com.shaya.loginsignup

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.shaya.loginsignup.databinding.ActivityAddBinding
import com.shaya.loginsignup.utils.Utils
import com.shaya.loginsignup.utils.Utils.loadImageViaFile
import com.shaya.loginsignup.utils.Utils.toGalleryImageFile
import com.shaya.loginsignup.viewmodels.AddFriendViewModel
import com.shaya.loginsignup.viewmodels.FriendViewModelFactory

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding
    var filePath: String? = null

    private val REQUIRED_PERMISSION_GALLERY = Manifest.permission.READ_EXTERNAL_STORAGE

    private val viewModel: AddFriendViewModel by viewModels {
        FriendViewModelFactory(BaseApplication.registrationDatabase.friendDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgFriend.setOnClickListener {
            if (allPermissionsGrantedForGallery()) {
                showGallery()
            }else{
                storagePermissionLauncher.launch(REQUIRED_PERMISSION_GALLERY)

            }
        }

        binding.btnAdd.setOnClickListener {
            addNewFriend()
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }
    }

    private fun addNewFriend() {
        viewModel.addNewItem(
            firstName = binding.editTextFirstName.text.toString(),
            lastName = binding.editTextLastName.text.toString(),
            authorUserName = Utils.getLoggedInUser().orEmpty(),
            filePath = filePath.orEmpty()
        )

    }

    private fun showGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        galleryImagePickerLauncher.launch(gallery)
    }

    private fun allPermissionsGrantedForGallery() = ContextCompat.checkSelfPermission(
        this, REQUIRED_PERMISSION_GALLERY
    ) == PackageManager.PERMISSION_GRANTED


    private var galleryImagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            imageUri?.let {
                val file = it.toGalleryImageFile()
                filePath = file?.path
                binding.imgFriend.loadImageViaFile(file)
            }
        }
    }

    private val storagePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            showGallery()
        } else {
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show()
        }
    }




}