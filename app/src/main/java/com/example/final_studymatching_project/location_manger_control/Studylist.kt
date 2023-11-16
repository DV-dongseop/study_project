package com.example.final_studymatching_project.location_manger_control

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.final_studymatching_project.R

class Studylist : AppCompatActivity() {
    private lateinit var list: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById(R.id.study_list)

        val data = ArrayList<String>()
        data.add("네이버")
        data.add("다음")
        data.add("구글")
        data.add("티스토리")
        data.add("계발에서 개발까지")

        val adapter = ArrayAdapter(this@Studylist, android.R.layout.simple_list_item_1, data)
        list.adapter = adapter
    }
}

