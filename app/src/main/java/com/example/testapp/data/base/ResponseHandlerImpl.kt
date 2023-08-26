package com.example.testapp.data.base

import com.example.testapp.data.cloud.CloudDataRequestState
import com.example.testapp.domain.DispatchersProvider
import kotlinx.coroutines.withContext
import retrofit2.Response

class ResponseHandlerImpl(
    private val dispatchersProvider: DispatchersProvider
) : ResponseHandler {

    override suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): CloudDataRequestState<T> {
        runCatching {
            withContext(dispatchersProvider.io()) { apiCall() }
        }.onSuccess { response ->
            if (response.isSuccessful) {
                val body = withContext(dispatchersProvider.default()) { response.body() }
                body?.let { return CloudDataRequestState.Success(data = body) }
            }
        }.onFailure { exception ->
            return CloudDataRequestState.Error(exception)
        }
        return CloudDataRequestState.Error(error = java.lang.IllegalStateException())
    }
}