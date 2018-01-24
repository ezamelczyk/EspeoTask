package me.ernestzamelczyk.espeotask.data.service

import me.ernestzamelczyk.espeotask.data.model.EspeoPosition
import retrofit2.Call
import retrofit2.http.GET

interface EspeoPositionApiService {

    @GET("/raw/uYCM5u0P")
    fun espeoPosition(): Call<EspeoPosition>
}