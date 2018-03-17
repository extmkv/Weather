package com.github.extmkv.weather.feature.home.location

import android.content.Intent
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.loading.LoadingContract
import com.github.extmkv.weather.base.loading.LoadingFragment
import com.massivedisaster.location.LocationManager
import com.massivedisaster.location.listener.OnLocationManager
import com.massivedisaster.location.utils.LocationError


abstract class LocationLoadingFragment<T : LoadingContract.Presenter<*>> : LoadingFragment<T>(), OnLocationManager {

    private lateinit var locationManager: LocationManager

    override fun onCreate() {
        locationManager = LocationManager()
        locationManager.onCreate(this)
    }

    override fun onDestroy() {
        locationManager.onDestroy()
        super.onDestroy()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        locationManager.onRequestPermissionsResult(requestCode, permissions, *grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        locationManager.onActivityResult(requestCode, resultCode)
    }

    override fun onPause() {
        super.onPause()
        locationManager.stopRequestLocation()
    }

    /**
     * Start request for a single location update.
     */
    protected fun requestLocation() {
        showLoading()
        locationManager.requestSingleLocation(true, this)
    }

    override fun onLocationError(locationError: LocationError) {
        when (locationError) {
            LocationError.TIMEOUT -> {
                showError(getString(R.string.error_getting_your_location))
            }
            else -> {
                showError(getString(R.string.please_activate_your_location))
            }
        }
    }

    override fun onPermissionsDenied() {
        showError(getString(R.string.error_grant_location_permission))
    }

    override fun onProviderEnabled() {}

    override fun onProviderDisabled() {}

    override fun onStopRequestUpdate() {}
}