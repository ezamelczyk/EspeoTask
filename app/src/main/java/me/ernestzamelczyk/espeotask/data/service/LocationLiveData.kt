package me.ernestzamelczyk.espeotask.data.service

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import com.google.android.gms.location.*

class LocationLiveData
constructor(context: Context) : LiveData<Location>() {

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            value = locationResult?.lastLocation
        }
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(this::setValue)
        if(hasActiveObservers()) {
            fusedLocationProviderClient.requestLocationUpdates(LocationRequest.create().setInterval(1000), locationCallback, null)
        }
    }

    override fun onInactive() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

}