package com.example.daggerhiltpractice.common.impl

import android.content.Context
import com.example.daggerhiltpractice.common.interfaces.PermissionService
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import javax.inject.Inject

class PermissionServiceImpl @Inject constructor(): PermissionService {
    override fun getPermission(
        context: Context,
        permissionList: Collection<String>,
        onAllPermissionGranted: () -> Unit
    ) {

    }

    override fun isPermissionGiven(context: Context, permissionType: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSinglePermission(
        context: Context,
        permissionType: String,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) {
        Dexter.withContext(context)
            .withPermission(
                permissionType
            )
            .withListener(object : com.karumi.dexter.listener.single.PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    onPermissionGranted.invoke()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    if(p0!!.isPermanentlyDenied) {
//                        PermissionSettingsDialog.show(context)
                    } else {
                        onPermissionDenied.invoke()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    permissionToken: PermissionToken?
                ) {
                    permissionToken?.continuePermissionRequest()
                }
            }).check()
    }
}