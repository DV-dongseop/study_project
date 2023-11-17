package com.example.final_studymatching_project

import android.content.Intent
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.final_studymatching_project.location_manger_control.MatchingMain
import com.example.final_studymatching_project.ui.theme.Final_StudyMatching_ProjectTheme
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Final_StudyMatching_ProjectTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
        //파이어베이스 실시간 데이터 체킹용
        val database = Firebase.database
        val myRef = database.getReference("Checking")
        myRef.setValue("Success")
    }

    @Composable
    fun MainContent() {
        // XML에서 정의된 버튼 사용
        Button(
            onClick = { navigateToMatchingMain() },
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Matching")
        }
    }

    private fun navigateToMatchingMain() {
        val intent = Intent(this, MatchingMain::class.java)
        startActivity(intent)


    }
}

