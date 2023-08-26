package com.example.testapp.presentation.di

import com.example.testapp.data.base.ResponseHandler
import com.example.testapp.data.base.ResponseHandlerImpl
import com.example.testapp.domain.DispatchersProvider
import org.koin.dsl.module

val appModule = module {

    single<ResponseHandler> { ResponseHandlerImpl(dispatchersProvider = get()) }

    single<DispatchersProvider> { DispatchersProvider.Base() }

}