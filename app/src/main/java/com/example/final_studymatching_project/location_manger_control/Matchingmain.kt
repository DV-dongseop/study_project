package com.example.final_studymatching_project.location_manger_control


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.final_studymatching_project.R


class MatchingMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matchingmain)

        // study_matching_btn 클릭 시 Studylist 액티비티로 이동
        val studyMatchingBtn = findViewById<Button>(R.id.study_matching_btn)
        studyMatchingBtn.setOnClickListener {
            val intent = Intent(this, Studylist::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.study_createbtn).setOnClickListener {
            val intent = Intent(this@MatchingMain, Roomcrate::class.java)
            startActivity(intent)
        }

    }
}

