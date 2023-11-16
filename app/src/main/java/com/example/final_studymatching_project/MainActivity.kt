package com.example.final_studymatching_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.final_studymatching_project.location_manger_control.MatchingMain
import com.example.final_studymatching_project.ui.theme.Final_StudyMatching_ProjectTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btn1)
        button.setOnClickListener {
            val intent = Intent(this, MatchingMain::class.java)
            startActivity(intent)
            finish()
        }
    }
}
