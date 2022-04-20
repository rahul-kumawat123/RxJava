package com.example.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Here the hot observable is not waiting for the second observer and continues its process.
         * (shows behaviour of hot observable)
         */

        val hotObservable = hotObservableBehaviour()

        hotObservable.connect()
        hotObservable.subscribe(
            {
                Log.d("hotObservable","onNext 1st: $it")
            },
            {
                Log.d("hotObservable","onError 1st : ${it.localizedMessage}")
            },
            {
                Log.d("hotObservable","Completed 1st")
            }
        )

        Thread.sleep(10000)
        hotObservable.subscribe(
            {
                Log.d("hotObservable","onNext 2nd: $it")
            },
            {
                Log.d("hotObservable","onError 2nd : ${it.localizedMessage}")
            },
            {
                Log.d("hotObservable","Completed 2nd")
            }
        )
    }
}