package me.ernestzamelczyk.espeotask.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import me.ernestzamelczyk.espeotask.data.model.EspeoPosition
import me.ernestzamelczyk.espeotask.data.service.EspeoPositionApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EspeoPositionRepository(private val espeoPositionApiService: EspeoPositionApiService) {

    fun getEspeoPosition(): LiveData<EspeoPosition> {

        val espeoLiveData: MutableLiveData<EspeoPosition> = MutableLiveData()

        espeoPositionApiService.espeoPosition().enqueue(object: Callback<EspeoPosition> {

            override fun onFailure(call: Call<EspeoPosition>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<EspeoPosition>?, response: Response<EspeoPosition>?) {
                espeoLiveData.value = response!!.body()
            }

        })

        return espeoLiveData

    }

}