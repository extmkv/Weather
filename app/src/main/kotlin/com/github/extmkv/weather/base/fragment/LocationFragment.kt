package com.github.extmkv.weather.base.fragment

import android.content.Intent
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.state.FragmentState
import com.github.extmkv.weather.base.state.StateContract
import com.massivedisaster.location.LocationManager
import com.massivedisaster.location.listener.OnLocationManager
import com.massivedisaster.location.utils.LocationError


abstract class LocationFragment<T : StateContract.Presenter<*>> : FragmentState<T>(), OnLocationManager {

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
                showError(getString(R.string.error_getting_your_location), { requestLocation() })
            }
            else -> {
                showError(getString(R.string.please_activate_your_location), { requestLocation() })
            }
        }
    }

    override fun onPermissionsDenied() {
        showError(getString(R.string.error_grant_location_permission), { requestLocation() })
    }

    override fun onProviderEnabled() {}

    override fun onProviderDisabled() {}

    override fun onStopRequestUpdate() {}
}