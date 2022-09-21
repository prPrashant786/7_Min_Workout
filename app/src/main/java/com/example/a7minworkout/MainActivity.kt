package com.example.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startbtn : FrameLayout = findViewById(R.id.fl_star)

        startbtn.setOnClickListener {
            Toast.makeText(this,"CLICKED",Toast.LENGTH_LONG).show()
        }
    }
}