package com.example.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minworkout.databinding.ActivityBmiBinding

class bmiActivity : AppCompatActivity() {

    var binding : ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarexer)
        if (supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate Your BMI"
        }
        binding?.toolbarexer?.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}