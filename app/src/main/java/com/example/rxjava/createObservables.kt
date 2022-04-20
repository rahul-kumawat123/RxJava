package com.example.rxjava

import android.util.Log
import io.reactivex.rxjava3.annotations.Nullable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
//    observableWithJust()
//    observableWithFromIterable()
//    observableWithCreate()
//    observableWithRange()

    observableAndObserver()

//    observableWithEmpty()
//    observableWithNever()

  //  observableWithDefer()
}

/**
 * This operator takes a list of arguments (maximum 10) and converts the items into Observable items.
 */
private fun observableWithJust(){
    println("OBSERVABLES USING JUST")
    val obs = Observable.just(1,2,3,4,5,6,324,3,43,5)
        .subscribe{
        println(it)
    }
}

/***
 * Used to convert a list into an Observable
 */
private fun observableWithFromIterable(){
    println("OBSERVABLES USING FROM_ITERABLE")

    val mList = listOf<Int>(1,23,23,42,35,32,5,24,3)
    val observable = Observable.fromIterable(mList)
        .subscribe{
        println(it)
    }
}

/**
 * This operator creates an Observable from scratch by calling observer methods programmatically.
 * An emitter is provided through which we can call the respective interface methods when needed.
 */
private fun observableWithCreate(){
    println("OBSERVABLES USING CREATE")

    val observable = Observable.create<Int> { emitter ->
            emitter.onNext(1)
            emitter.onNext(21)
            emitter.onNext(23)
            emitter.onNext(334)
            emitter.onNext(343)
            //emitter.onNext(null)
            emitter.onComplete()
    }

    observable.subscribe(
        {
            println(it)
        },
        {
            println("There is an error: " + it.localizedMessage)
        },
        {
            println("Completed")
        }
    )
}

/**
 * This operator is used to create an observable that emits a range of integers.
 *  It takes two arguments: the starting index and the length.
 *  Always provides Integer Observables
 */
private fun observableWithRange(){
    println("OBSERVABLES USING RANGE")

    val observable = Observable.range(5,20)
        .subscribe{ println(it)}
}


private fun observableAndObserver(){

    val latch = CountDownLatch(1)

    val observable = Observable.just(1,2,3,4,5)

    val observer = object :Observer<Int>{
        override fun onSubscribe(d: Disposable) {
          
        }

        override fun onNext(t: Int) {
            println(t)
        }

        override fun onError(e: Throwable) {
            print("ERROR : "+e.stackTrace)
        }

        override fun onComplete() {
            print("Completed")
        }

    }

    observable.subscribe(observer)

    latch.await()
    //Thread.sleep(1000000)
}


/**
 * Returns an Observable which emits no items to the observer and invokes onComplete method.
 */
private fun observableWithEmpty(){
    val observable = Observable.empty<Any>()

    observable.subscribe(
        {
            println(it)
        },
        {
            print(it.localizedMessage)
        },
        {
            print("Completed")
        }
    )
}


/**
 * Returns an Observable which does not emits anything or invokes anything to an observer.
 */
private fun observableWithNever(){
     val observable = Observable.never<Any>()

    observable.subscribe(
        {
            println(it)
        },
        {
            print(it.localizedMessage)
        },
        {
            print("Completed")
        }
    )

    Thread.sleep(3000)
}


/**
 * Returns a new observable for every observer.
 * Can be used anywhere in a process if a value is changed and the change is to be reflected in the observer.
 */
private fun observableWithDefer(){
    val start = 3
    var count = 5

    //without defer
    /*val observable = Observable.range(start,count)

    observable.subscribe{ println("Observer 1 : $it")}
    count = 2
    observable.subscribe{ println("Observer 2 : $it")}*/

    //with defer
    val observable = Observable.defer{
        println("New Observabel is created with start = $start and count = $count")
        Observable.range(start, count)}

    observable.subscribe{ println("Observer 1 : $it")}
    count = 2
    observable.subscribe{ println("Observer 2 : $it")}
}

