package com.andreamw96.moviecatalogue.data.source.remote


class ApiResponse<T>(var status: StatusResponse, var body: T?, var message: String?) {

    companion object {

        fun <T> success(body: T?): ApiResponse<T> {
            return ApiResponse(StatusResponse.SUCCESS, body, null)
        }

        fun <T> empty(msg: String, body: T?): ApiResponse<T> {
            return ApiResponse(StatusResponse.EMPTY, body, msg)
        }

        fun <T> error(msg: String, body: T?): ApiResponse<T> {
            return ApiResponse(StatusResponse.ERROR, body, msg)
        }
    }

    enum class StatusResponse {
        SUCCESS,
        EMPTY,
        ERROR
    }
}