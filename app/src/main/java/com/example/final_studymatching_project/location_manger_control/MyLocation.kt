package com.example.final_studymatching_project.location_manger_control

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MyLocation(private val activity: Activity) : ActivityCompat.OnRequestPermissionsResultCallback {
    private val TAG = "MyLocation"
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    private lateinit var mLastLocation: Location
    private val REQUEST_PERMISSION_LOCATION = 10
    private val GPS_UTIL_LOCATION_PERMISSION_REQUEST_CODE = 100
    private lateinit var mLocationRequest: LocationRequest
    private var currentLatitude: Double = 0.0
    private var currentLongitude: Double = 0.0
    private lateinit var databaseReference: DatabaseReference

    init {
        // 위치 정보 요청을 위한 LocationRequest 초기화
        mLocationRequest = LocationRequest.create()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 1000
    }


    fun startLocationUpdates() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { nonNullableLocation ->
                mLastLocation = nonNullableLocation
                currentLatitude = mLastLocation.latitude
                currentLongitude = mLastLocation.longitude
                Log.w(TAG, "latitude: $currentLatitude, longitude: $currentLongitude")
                val database = Firebase.database
                databaseReference = database.getReference("example")
                val locationMap = mapOf(
                    "latitude" to mLastLocation.latitude,
                    "longitude" to mLastLocation.longitude
                )
                databaseReference.setValue(locationMap)
                    .addOnSuccessListener {
                        Log.d(TAG, "Location data successfully saved to Firebase")
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "Error saving location data to Firebase: $it")
                    }
            }
        }
    }

    // 위치 권한이 있는지 확인하는 메서드
    private fun checkPermissionForLocation(context: Context): Boolean {
        // Android 6.0 Marshmallow 이상에서는 위치 권한에 추가 런타임 권한이 필요
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                // 권한이 없으므로 권한 요청 알림 보내기
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSION_LOCATION
                )
                false
            }
        } else {
            true
        }
    }

    fun requestLocationPermission() {
        if (checkPermissionForLocation(activity)) {
            startLocationUpdates()
        }
    }

    // 사용자에게 권한 요청 후 결과에 대한 처리 로직
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == GPS_UTIL_LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 부여되었을 때 위치 업데이트 요청 시작
                mLocationRequest?.let {
                    mFusedLocationProviderClient?.requestLocationUpdates(it, mLocationCallback, null)
                }
            } else {
                Log.d("ttt", "onRequestPermissionsResult() _ 권한 허용 거부")
                Toast.makeText(activity, "권한이 없어 해당 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 현재 위도 반환
    fun getCurrentLatitude(): Double {
        return currentLatitude
    }

    // 현재 경도 반환
    fun getCurrentLongitude(): Double {
        return currentLongitude
    }

    fun onPause() {
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        mFusedLocationProviderClient?.removeLocationUpdates(mLocationCallback)

    }

}