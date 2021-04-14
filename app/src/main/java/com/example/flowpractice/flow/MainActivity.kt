package com.example.flowpractice.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowpractice.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flow = flow {
            for (i in 1..10){
                emit(i)
                delay(1000L)
            }
        }

        /**
         if you do like below , you have to wait 3s because this coroutine runs same thread!!

        GlobalScope.launch {
        flow.collect {
        println(it)

        // Just Imagine you buffer this consumer actions!!
        delay(2000L)
        }
        }
         this is the solution to separate coroutine thread!!*/

        // If you delay 2s you just write buffer after this implementation, coroutine runs different thread!!
        GlobalScope.launch {
            flow.buffer().filter{
                it % 2 == 0
            }.map{
                it * it
            }.collect{
                println(it)
                delay(2000L)
            }
        }
    }
}