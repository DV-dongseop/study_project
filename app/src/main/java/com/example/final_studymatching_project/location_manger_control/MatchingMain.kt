package com.example.final_studymatching_project.location_manger_control

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.final_studymatching_project.R
import com.example.final_studymatching_project.location_manger_control.MyLocation

class MatchingMain : AppCompatActivity() {
    private val TAG = "MatchingMain"
    private lateinit var myLocation: MyLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matchingmain)

        // MyLocation 인스턴스 생성
        myLocation = MyLocation(this)

        // 위치 권한 부여 및 확인
        myLocation.requestLocationPermission()

        // 위치 정보 저장을 위한 함수 호출
        myLocation.startLocationUpdates()

        // 현재 위도 및 경도 가져오기
        val currentLatitude = myLocation.getCurrentLatitude()
        val currentLongitude = myLocation.getCurrentLongitude()
        Log.w(TAG, "Current latitude: $currentLatitude, longitude: $currentLongitude")
    }
    @Composable
    fun MainContent() {
        // XML에서 정의된 버튼 사용
        Button(
            onClick = { navigateToMatchingMain() },
        ) {}
    }

    private fun navigateToMatchingMain() {
        val intent = Intent(this, MatchingMain::class.java)
        startActivity(intent)
    }
}
