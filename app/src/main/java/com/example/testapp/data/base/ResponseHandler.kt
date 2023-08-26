package com.example.testapp.data.base

import com.example.testapp.data.cloud.CloudDataRequestState
import retrofit2.Response


interface ResponseHandler {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): CloudDataRequestState<T>

}