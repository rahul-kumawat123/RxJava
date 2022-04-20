package com.example.rxjava

import androidx.annotation.AnyRes
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*

fun main(args : Array<String>){
//    createSingle()
//    createMaybe()
    createCompletable()
}


/**
 * A Single is an observable that only emits one item and then completes.
 * The single ensures that one item will be sent, so it’s super-useful when we want to ensure we haven’t got empty outputs.
 */
private fun createSingle(){
    val s = Single.just(2)
    s.subscribe { item -> print(item) }
}


/**
 * Maybe works in a similar way to single, but with a particular property: it can complete without emitting a value.
 * This is useful when we have optional emissions, for example when we want to get a logged user but we are not signed in yet.
 */
private fun createMaybe(){

    Maybe.empty<Objects>().subscribe {
        object : MaybeObserver<Objects> {
            override fun onSubscribe(d: Disposable) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(t: Objects) {
                print(t)
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                print("Completed")
            }

        }
    }
}

/**
 * Completable is useful when we need to carry out actions that don’t require a specific output
 * (like when we make a login or send data, or when we simply need an OK/KO).
 */
private fun createCompletable(){

    val s = Single.just(2)
    Completable.fromSingle<Any> {  s.subscribe { item -> print(item) }}
}