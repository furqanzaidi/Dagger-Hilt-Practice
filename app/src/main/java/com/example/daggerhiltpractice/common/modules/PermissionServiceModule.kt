package com.example.daggerhiltpractice.common.modules

import com.example.daggerhiltpractice.common.impl.PermissionServiceImpl
import com.example.daggerhiltpractice.common.interfaces.PermissionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PermissionServiceModule {
    @Provides
    @Singleton
    fun providePermissionService(): PermissionService {
        return PermissionServiceImpl()
    }
}