package me.ernestzamelczyk.espeotask.data.model

import com.google.android.gms.maps.model.LatLng

data class EspeoPosition(
    val latitude: Double,
    val longitude: Double,
    val name: String
    ) {

    fun latLng(): LatLng {
        return LatLng(latitude, longitude)
    }
}