package com.lingo.stock.libs.deviceid

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyDeviceIdModule {

    @Provides
    @Singleton
    fun myDeviceId(@ApplicationContext context: Context): MyDeviceId =
        MyDeviceIdImpl(context.filesDir)
}