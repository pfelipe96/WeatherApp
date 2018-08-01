package com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.paulofelipeoliveirasouza.weatherapp.MainApplication
import com.example.paulofelipeoliveirasouza.weatherapp.R
import com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.widget.TextView
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.*
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener

class MainActivity : AppCompatActivity(), MainActivityInterface, PlaceSelectionListener {

    @Inject
    lateinit var presenter: MainPresenter

    private val REQUEST_LOCATION_PERMISSION = 110

    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mGeoDataClient: GeoDataClient
    private lateinit var mPlaceDetectionClient: PlaceDetectionClient

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MainApplication).applicationComponent.inject(this)

        presenter.attachView(this)

        getPermissionLocation()

        mGeoDataClient = Places.getGeoDataClient(this, null)
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null)

        val autoCompleteFragment = fragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment
        autoCompleteFragment.setOnPlaceSelectedListener(this)
    }


    override fun setDataInViews(nameCity: String, minTemperature: String, setImage: String, nowTemperature: String, forecast: String, maxTemperature: String, humidity: String, sunrise: String, sunset: String) {
        name_city.text = nameCity
        min_weather.text = minTemperature

        now_weather.text = nowTemperature
        forecast_weather.text = forecast

        max_weather.text = maxTemperature

        set_humidity.text = humidity
        set_sunrise.text = sunrise
        set_sunset.text = sunset

        Glide.with(this).load("http://openweathermap.org/img/w/$setImage.png").into(image_weather)

        setProgressBar(View.GONE)
        setVisibleFrameLayout(View.VISIBLE)
    }

    override fun setProgressBar(visible: Int) {
        progress_bar.visibility = visible
    }

    override fun setVisibleFrameLayout(visible: Int) {
        frame_layout.visibility = visible
    }

    override fun getPermissionLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        } else {
            getLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    dialogRepeatPermission()
                }
        }
    }

    @SuppressLint("MissingPermission")
    override fun getLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationProviderClient.lastLocation.addOnCompleteListener {
            if (it.isSuccessful) {
                presenter.getLocationUser(it.result)
            }
        }
    }

    private fun dialogRepeatPermission() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialog)
        builder.setTitle(getString(R.string.title_repeat_permission))
        builder.setMessage(getString(R.string.message_repeat_permission))
        builder.setPositiveButton("Sim") { dialog, _ ->

            dialog.dismiss()
        }
        builder.setNegativeButton("Não") { dialog, _ ->
            dialog.dismiss()
            getPermissionLocation()
        }
        builder.setOnDismissListener {
            it.dismiss()
        }
        builder.create().show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun snackBarIsNetWorking() {
        val snackBar = Snackbar
                .make(main_view, getString(R.string.dialog_title_not_working_networking), 20000)
                .setActionTextColor(getColor(R.color.colorAccent))
                .setAction("Ok") {
                    val intent = intent
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    finish()
                    startActivity(intent)
                }

        val sbView = snackBar.view
        val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        snackBar.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun snackBarOnError(message: String) {
        val snackBar = Snackbar
                .make(main_view, message, 20000)
                .setActionTextColor(getColor(R.color.colorAccent))
                .setAction("Ok") {
                }

        val sbView = snackBar.view
        val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        snackBar.show()
    }

    override fun isNetworkAvaliableToContext(): Boolean {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo

        var isAvailable = false

        if (networkInfo != null && networkInfo.isConnected) {
            isAvailable = true
        }
        return isAvailable
    }

    override fun onPlaceSelected(p0: Place?) {
        p0?.let {
            presenter.getDataByCity(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onError(p0: Status?) {
        snackBarOnError("Infelizmente ocorreu um erro, por gentileza tente novamente mais tarde.")
    }

}
