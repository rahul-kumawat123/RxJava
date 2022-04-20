package com.example.rxjava

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    //hotObservableUsingConnectedObservable()

}

    private fun hotObservableUsingConnectedObservable(){
        val observable = ConnectableObservable.just(1,2,3,4,5).publish()

   //   will not work because connect() is not called

        observable.subscribe{ println("Observer 1: "+it) }


    //  shows both observers

        /*observable.subscribe{ println("Observer 1: "+it) }
        observable.subscribe{println("Observer 2: "+it)}
        observable.connect()*/

    //  shows only 1 which is connected and the other one is already emitted from the observable

        /*observable.subscribe{ println("Observer 1: "+it) }
        observable.subscribe{println("Observer 2: "+it)}
        observable.connect()
        observable.subscribe{println("Observer 2: "+it)}*/
    }


/**
 *  Example of Hot Observables Behaviour
 */
    public fun hotObservableBehaviour(): ConnectableObservable<Long>{
        return Observable.interval(1,TimeUnit.SECONDS).publish()
    }