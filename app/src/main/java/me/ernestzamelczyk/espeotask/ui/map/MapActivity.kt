package me.ernestzamelczyk.espeotask.ui.map

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import dagger.android.AndroidInjection
import me.ernestzamelczyk.espeotask.R
import javax.inject.Inject
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Polyline
import kotlinx.android.synthetic.main.activity_map.*
import android.location.LocationManager
import android.view.View


class MapActivity: AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private const val PERMISSION_REQUEST_LOCATION = 1
    }

    @Inject
    lateinit var viewModel: MapViewModel

    private var markerOptions: MarkerOptions? = null
    private var polylineOptions: PolylineOptions? = null
    private var googleMap: GoogleMap? = null
    private var bottomSheetBehavior: BottomSheetBehavior<View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
        googleMap?.setOnMapClickListener(this::onMapClick)
//        coordinatorLayout.setOnClickListener(this::onMapClick)
        //Enable map location
        setMapLocationEnabled()

        // Fetch espeo position from pastebin
        viewModel.espeoPosition.observe(this, Observer {
            if(it != null) {
                val espeoLatLng = LatLng(it.latitude, it.longitude)

                // make marker icon from drawable
                val icon = BitmapDescriptorFactory.fromResource(R.drawable.espeo_marker)

                // compose marker options from latlng title and icon
                markerOptions = MarkerOptions()
                        .position(espeoLatLng)
                        .title(it.name)
                        .icon(icon)

                // add marker to map and show title
                googleMap?.addMarker(markerOptions)?.showInfoWindow()
            }

        })
    }

    /**
     * not working - why? I dunno
     */
    private fun onMapClick(latLng: LatLng) {
        if(bottomSheetBehavior == null) {
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        }
        if(bottomSheetBehavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun onLocationChanged(location: Location?) {
        val espeoPosition = viewModel.espeoPosition.value
        if(location != null && espeoPosition != null) {
            if(polylineOptions == null) {
                viewModel.getGoogleDirections(resources.getString(R.string.google_services_key), LatLng(location.latitude, location.longitude), espeoPosition.latLng()).observe(this, Observer {
                    if(it != null) {
                        polylineOptions = it
                        googleMap?.let {
                            it.animateCamera(moveToBounds(it.addPolyline(polylineOptions)))
                        }
                    }
                })
            }
            val toLocation = Location(LocationManager.GPS_PROVIDER)
            toLocation.latitude = espeoPosition.latitude
            toLocation.longitude = espeoPosition.longitude
            val dist = location.distanceTo(toLocation)/1000
            distance.text = String.format("%10.2f km", dist)
        }
    }

    private fun setMapLocationEnabled() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                googleMap?.isMyLocationEnabled = true
                // observe our position
                viewModel.locationLiveData.observe(this, Observer { onLocationChanged(it) })
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) -> showRequestLocationPermissionDialog()
            else -> requestLocationPermissions()
        }
    }

    private fun showRequestLocationPermissionDialog() {
        val dialog = AlertDialog
                .Builder(this)
                .setCancelable(false)
                .setTitle("Permissions request")
                .setMessage("If you want to display a map you must give location permissions to the application")

        dialog.setPositiveButton("Ok", { _, _ -> requestLocationPermissions() })
        dialog.setNegativeButton("Cancel", { _, _ -> finish() })
        dialog.show()
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_LOCATION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    setMapLocationEnabled()
                } else {
                    showRequestLocationPermissionDialog()
                }
            }
            else -> return
        }
    }

    private fun calculateDistance(from: LatLng, to: LatLng): Float {
        val fromLocation = Location(LocationManager.GPS_PROVIDER)
        fromLocation.latitude = from.latitude
        fromLocation.longitude = from.longitude

        val toLocation = Location(LocationManager.GPS_PROVIDER)
        toLocation.latitude = to.latitude
        toLocation.longitude = to.longitude

        return fromLocation.distanceTo(toLocation)
    }

    private fun moveToBounds(p: Polyline): CameraUpdate {
        val builder = LatLngBounds.Builder()
        for (i in 0 until p.points.size) {
            builder.include(p.points[i])
        }
        val bounds = builder.build()
        val padding = 200
        return CameraUpdateFactory.newLatLngBounds(bounds, padding)
    }

}