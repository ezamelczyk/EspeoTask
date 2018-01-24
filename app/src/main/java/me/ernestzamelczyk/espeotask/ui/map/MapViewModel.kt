package me.ernestzamelczyk.espeotask.ui.map

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.RequestResult
import com.akexorcist.googledirection.model.Direction
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import me.ernestzamelczyk.espeotask.R
import me.ernestzamelczyk.espeotask.data.model.EspeoPosition
import me.ernestzamelczyk.espeotask.data.repository.EspeoPositionRepository
import me.ernestzamelczyk.espeotask.data.service.LocationLiveData
import me.ernestzamelczyk.espeotask.di.AppContext
import javax.inject.Inject

class MapViewModel @Inject constructor(espeoPositionRepository: EspeoPositionRepository, @AppContext context: Context) : ViewModel() {

    val espeoPosition: LiveData<EspeoPosition> = espeoPositionRepository.getEspeoPosition()
    val locationLiveData: LocationLiveData = LocationLiveData(context)

    fun getGoogleDirections(apiKey: String, from: LatLng, to: LatLng): LiveData<PolylineOptions> {
        val liveData: MutableLiveData<PolylineOptions> = MutableLiveData()
        GoogleDirection.withServerKey(apiKey)
                .from(from)
                .to(to)
                .execute(object : DirectionCallback {
                    override fun onDirectionSuccess(direction: Direction, rawBody: String?) {
                        if (direction.status == RequestResult.OK) {
                            val route = direction.routeList[0]
                            val leg = route.legList[0]
                            val steps = leg.directionPoint
                            val polylineOptions = PolylineOptions()
                            polylineOptions.addAll(steps)
                            liveData.value = polylineOptions
                        }
                    }

                    override fun onDirectionFailure(t: Throwable?) {

                    }

                })
        return liveData
    }

}