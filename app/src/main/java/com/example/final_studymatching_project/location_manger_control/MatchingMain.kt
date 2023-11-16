package com.example.final_studymatching_project.location_manger_control

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.final_studymatching_project.MainActivity
import com.example.final_studymatching_project.R
import com.example.final_studymatching_project.location_manger_control.MyLocation
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MatchingMain : AppCompatActivity() {
    private val TAG = "MatchingMain"
    private lateinit var myLocation: MyLocation
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matchingmain)

        // MyLocation 인스턴스 생성
        myLocation = MyLocation(this)

        // 위치 권한 부여 및 확인
        myLocation.requestLocationPermission()

        // 위치 정보 저장을 위한 함수 호출
        myLocation.startLocationUpdates()


        val button: Button = findViewById(R.id.home_btn)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}


