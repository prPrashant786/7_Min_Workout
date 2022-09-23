package com.example.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding : ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exersiseTimer: CountDownTimer? = null
    private var exersiseProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarexer)

        if (supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarexer?.setNavigationOnClickListener {
            onBackPressed()
        }

        setUpResttimer()
        
    }

    private fun setUpResttimer(){
        binding?.flprogress?.visibility = View.VISIBLE
        binding?.flExerprogress?.visibility = View.INVISIBLE
        if (restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressbar()
    }
    private fun setUpExerciseTimer(){
        binding?.flprogress?.visibility = View.INVISIBLE
        binding?.flExerprogress?.visibility = View.VISIBLE


        if (exersiseTimer!=null){
            exersiseTimer?.cancel()
            exersiseProgress = 0
        }

        setExersiseProgress()

    }

    private fun setRestProgressbar(){
        binding?.tvtittle?.text = "GET READY FOR EXERCISE"
        binding?.progressbar?.progress = restProgress
        // To implement CountDown
        restTimer = object : CountDownTimer(11000,1000){// totaltime , after that time we do
            override fun onTick(millisUntilFinished: Long) {
                restProgress++;
                binding?.progressbar?.progress = 11 - restProgress
                binding?.tvTimer?.text =  (11 - restProgress).toString()
            }

            override fun onFinish() {
                setUpExerciseTimer()
            }

        }.start()
    }


    private fun setExersiseProgress(){
        binding?.tvtittle?.text = "DO THE EXERCISE"
        binding?.Exerprogressbar?.progress = exersiseProgress

        exersiseTimer = object : CountDownTimer(30000,1000){// totaltime , after that time we do
        override fun onTick(millisUntilFinished: Long) {
            exersiseProgress++;
            binding?.Exerprogressbar?.progress = 30 - exersiseProgress
            binding?.tvExerTimer?.text = (30 - exersiseProgress).toString()
        }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,"Exercise Done",Toast.LENGTH_LONG).show()

            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
            binding?.flprogress?.visibility = View.VISIBLE

        }
        if (exersiseTimer!=null){
            exersiseTimer?.cancel()
            exersiseProgress = 0
        }
        binding = null
    }
}