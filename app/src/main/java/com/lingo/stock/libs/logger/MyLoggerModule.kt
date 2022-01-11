package com.lingo.stock.libs.logger

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object MyLoggerModule {

    @Provides
    fun myLogger(@ActivityContext context: Context): MyLogger =
        MyLoggerImpl("A[${context::class.simpleName!!}]")
}