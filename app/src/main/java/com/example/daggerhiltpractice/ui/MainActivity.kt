package com.example.daggerhiltpractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.daggerhiltpractice.common.viewmodels.MediaViewModel
import com.example.daggerhiltpractice.R
import com.example.daggerhiltpractice.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MediaViewModel
    private var binding:ActivityMainBinding?=null
    private var gallaryActivityResultLaunch: ActivityResultLauncher<Intent>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpResultLauncher()
        viewModel=ViewModelProvider(this)[MediaViewModel::class.java]
        binding?.hell?.setOnClickListener {
            viewModel.requestGalleryPermission(this,
                onPermissionGranted = {
                    val pickIntent= Intent(Intent.ACTION_PICK)
                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*")
                    gallaryActivityResultLaunch?.launch(pickIntent)
                }, onPermissionDenied = {
                    Toast.makeText(this, "Permission Required", Toast.LENGTH_SHORT).show()
                })
        }
    }
    private fun setUpResultLauncher(){
        gallaryActivityResultLaunch=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if (result.resultCode== RESULT_OK){
                Toast.makeText(this, "${result.data}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}