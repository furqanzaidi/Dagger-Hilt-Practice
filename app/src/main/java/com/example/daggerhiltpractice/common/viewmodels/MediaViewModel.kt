package com.example.daggerhiltpractice.common.viewmodels

import android.Manifest
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.daggerhiltpractice.common.interfaces.PermissionService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(private val permissionService: PermissionService):ViewModel(){
    fun requestGalleryPermission(
        context: Context,
        onPermissionDenied: () -> Unit,
        onPermissionGranted: () -> Unit
    ) {
        permissionService.getSinglePermission(
            context,
            permissionType = Manifest.permission.READ_EXTERNAL_STORAGE,
            onPermissionGranted = onPermissionGranted,
            onPermissionDenied = onPermissionDenied
        )
    }
}