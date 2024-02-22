package com.example.daggerhiltpractice.common.interfaces

import android.content.Context
import javax.inject.Singleton

interface PermissionService {
    fun getPermission(context: Context, permissionList:Collection<String>, onAllPermissionGranted:()->Unit)
    fun isPermissionGiven(context: Context, permissionType:String):Boolean
    fun getSinglePermission(context: Context, permissionType: String, onPermissionGranted: () -> Unit, onPermissionDenied:()->Unit)
}