package com.example.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.*
import java.io.IOException
import java.lang.Exception
import java.util.*

fun main(args: Array<String>){

  //  doOnError()
  //  onErrorResumeNext()
  //  onErrorReturn()
  //  onErrorReturnItem()
  //  retryOperator()
    retryUntilOperator()
}

/**
 *  doOnError can invoke whatever action that is needed when there is an error.
 *  Here the error still breaks the standard sequence flow.
 */
private fun doOnError(){
   val observable = error<Throwable>(IOException("This is an example error"))
       .doOnError{ println("Error : ${it.localizedMessage}")}
       .subscribe(
           {
               println(it)
           },
           {
               print("Subscribed Error : ${it.localizedMessage}")
           },
           {
               print("Completed")
           }
       )
}


/**
 * onErrorResumeNext(), which replaces the current stream with an entirely new Observable.
 */
private fun onErrorResumeNext(){
    val observable = Observable.just(1,2,3,4,5)
        .map { it/(4-it) }
        .onErrorResumeNext { Observable.just(10,11,12,13) }
        .subscribe(
            {
                println(it)
            },
            {
                print("Subscribed Error : ${it.localizedMessage}")
            },
            {
                print("Completed")
            }
        )
}


/**
 * onErrorReturn replaces onError with a single onNext(value) followed by onCompleted()
 */
private fun onErrorReturn(){
    val observable = error<Throwable>(IOException("This is an example error"))
        .onErrorReturn { if (it is IOException) return@onErrorReturn IOException("This is an IOException Error")
        else return@onErrorReturn Exception("Just a normal error")}
        .subscribe(
            {
                println(it)
            },
            {
                print("Subscribed Error : ${it.localizedMessage}")
            },
            {
                print("Completed")
            }
        )
}

/**
 * Though we can invoke actions with doOnError, but the error still breaks the standard sequence flow.
 * Sometimes we want to resume the sequence with a default option, that's what onErrorReturnItem does
 */
private fun onErrorReturnItem(){
    val observable = Observable.just(1,2,3,4,5)
        .map { it/(4-it) }
        .onErrorReturnItem(10)
        .subscribe(
            {
                println(it)
            },
            {
                print("Subscribed Error : ${it.localizedMessage}")
            },
            {
                print("Completed")
            }
        )
}


/**
 * Retry can be used if a source Observable emits an error, resubscribe to it in the hopes that it will complete without error
 */
private fun retryOperator(){
    val observable = Observable.just(1,2,3,4,6,7)
        .map { it/(4-it) }
        .retry(3)
        .subscribe(
        {
            println(it)
        },
        {
            print("Subscribed Error : ${it.localizedMessage}")
        },
        {
            print("Completed")
        }
    )


    //retry with predicate

    /*Observable.error<Exception>(IOException("This is an example error"))
        .retry(5){
            if (it is IOException){
                println("retrying...")
                return@retry true
            } else return@retry false
        }
        .subscribe(
        {
            println(it)
        },
        {
            print("Subscribed Error : ${it.localizedMessage}")
        },
        {
            print("Completed")
        }
    )*/
}


/**
 * RetryUntil works just like retry but we can provide custom conditions upto which it resubscribes
 */
private fun retryUntilOperator(){
    var count = 0
    error<Throwable>(IOException("This is an example error"))
        .doOnError{
            println(count)
            count++
        }
        .retryUntil(){
            println("Retrying....")
            return@retryUntil count >= 5
        }
        .subscribe(
        {
            println(it)
        },
        {
            print("Subscribed Error : ${it.localizedMessage}")
        },
        {
            print("Completed")
        }
    )
}