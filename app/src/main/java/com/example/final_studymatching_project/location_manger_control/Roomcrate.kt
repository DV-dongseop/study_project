package com.example.final_studymatching_project.location_manger_control

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.final_studymatching_project.R

class Roomcrate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roomcrate)

        findViewById<Button>(R.id.cancelbtn).setOnClickListener {
            val intent = Intent(this@Roomcrate, MatchingMain::class.java)
            startActivity(intent)
        }
    }
}