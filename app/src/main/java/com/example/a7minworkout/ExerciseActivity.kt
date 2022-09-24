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

    private var exerciseList : ArrayList<EcerciseModel>? = null
    private var exerciseidx : Int = -1;


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


        exerciseList = Constant.defaultExerciseList()

        setUpResttimer()
        
    }

    private fun setUpResttimer(){
        binding?.flprogress?.visibility = View.VISIBLE
        binding?.tvtittle?.visibility = View.VISIBLE

        binding?.flExerprogress?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.GONE
        binding?.tvExerciseName?.visibility = View.GONE
        if (restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
        }
        binding?.tvtittle?.text = "Next Exercise ${exerciseList?.get(exerciseidx+1)?.getName()}"
        setRestProgressbar()
    }
    private fun setUpExerciseTimer(){
        binding?.flprogress?.visibility = View.INVISIBLE
        binding?.tvtittle?.visibility = View.INVISIBLE

        binding?.flExerprogress?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE


        if (exersiseTimer!=null){
            exersiseTimer?.cancel()
            exersiseProgress = 0
        }

        binding?.tvExerciseName?.text = exerciseList?.get(exerciseidx)?.getName()
        binding?.ivImage?.setImageResource(exerciseList!![exerciseidx].getImage())

        setExersiseProgress()

    }

    private fun setRestProgressbar(){

        binding?.progressbar?.progress = restProgress
        // To implement CountDown
        restTimer = object : CountDownTimer(11000,1000){// totaltime , after that time we do
            override fun onTick(millisUntilFinished: Long) {
                restProgress++;
                binding?.progressbar?.progress = 11 - restProgress
                binding?.tvTimer?.text =  (11 - restProgress).toString()
            }

            override fun onFinish() {
                exerciseidx++
                setUpExerciseTimer()
            }

        }.start()
    }


    private fun setExersiseProgress(){

        binding?.Exerprogressbar?.progress = exersiseProgress

        exersiseTimer = object : CountDownTimer(30000,1000){// totaltime , after that time we do
        override fun onTick(millisUntilFinished: Long) {
            exersiseProgress++;
            binding?.Exerprogressbar?.progress = 30 - exersiseProgress
            binding?.tvExerTimer?.text = (30 - exersiseProgress).toString()
        }

            override fun onFinish() {
                if (exerciseidx < exerciseList?.size!! - 1){
                    setUpResttimer()
                }
                else {
                    Toast.makeText(this@ExerciseActivity,
                    "DONE",Toast.LENGTH_LONG).show()
                    finish()
                }
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