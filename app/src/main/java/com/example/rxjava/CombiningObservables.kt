package com.example.rxjava

import io.reactivex.rxjava3.core.Observable
import java.util.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>){
   // mergeOperator()
   // mergeArrayOperator()
   // mergeWithOperator()
   // zipOperator()
   // zipWithOperator()
   // flatMapOperator()
   // concatOperator()
    concatWithOperator()
}

/**
 * Merge operator subscribes to all the passed sources simultaneously and relays their emissions as soon as they’re available.
 */
private fun mergeOperator(){
    val observable1 = Observable.just(1,2,3,4,5)
    val observable2 = Observable.just(6,7,8,9)

    Observable.merge(observable1,observable2).subscribe { println(it) }

    // merging as a list

    /*val observable1 = Observable.just(1,2,3,4,5)
    val observable2 = Observable.just(6,7,8,9)
    val observable3 = Observable.just(10,11,12)
    val observable4 = Observable.just(13,14)
    val observable5 = Observable.just(15)

    val mlist = listOf<Observable<Int>>(observable1,observable2,observable3,observable4,observable5)

    Observable.merge(mlist).subscribe { println(it)}*/

}


/**
 * Returns new Observable instance and works just like merge but can take infinite no. of observables
 */
private fun mergeArrayOperator(){
    val observable1 = Observable.just(1,2,3,4,5)
    val observable2 = Observable.just(6,7,8,9)
    val observable3 = Observable.just(10,11,12)
    val observable4 = Observable.just(13,14)
    val observable5 = Observable.just(15)

    Observable.mergeArray(observable1,observable2,observable3,observable4,observable5)
        .subscribe { println(it) }
}


/**
 * Returns combined items emitted by multiple ObservableSources so that they appear as a single ObservableSource
 */
private fun mergeWithOperator(){
    val observable1 = Observable.just(1,2,3,4,5)
    val observable2 = Observable.just(6,7,8,9)

    observable1.mergeWith(observable2).subscribe { println(it) }

    // merging infinite observables

    /*val observable1 = Observable.interval(1,TimeUnit.SECONDS)
        .map { "observable 1 :$it" }
    val observable2 = Observable.interval(3,TimeUnit.SECONDS)
        .map {  "observable 2 :$it"  }

    observable1.mergeWith(observable2).subscribe { println(it) }

    Thread.sleep(10010)*/
}


/**
 * Zip takes an emissions from each Observable source and combine it into a single emission.
 */
private fun zipOperator(){
    val observable1 = Observable.just(1,2,3,4,5)
    val observable2 = Observable.just(6,7,8,9 ,20)

    Observable.zip(observable1,observable2, { observable1, observable2 -> observable1+ observable2})
        .subscribe { println(it) }

    // another example using list

    /*val observable1 = Observable.just(1,2,3,4,5)
    val observable2 = Observable.just(6,7,8,9 ,20)
    val observable3 = Observable.just(23,72,81,90 ,20)

    val mList = listOf<Observable<Int>>(observable1,observable2,observable3)

    Observable.zip(mList) { Arrays.toString(it) }
        .subscribe { println(it) }*/
}

/**
 * The zipWith operator always takes two parameters. The first parameter may be either a simple Observable, or an iterable
 */
private fun zipWithOperator(){
    val observable1 = Observable.just(1,2,3,4,5)
    val observable2 = Observable.just(6,7,8,9 ,20)

    observable1.zipWith(observable2, { observable1, observable2 -> observable2 - observable1})
        .subscribe { println(it) }
}


/**
 * This operator is used like the map operator to transform items emitted from an observable,
 * but instead of returning the item, it returns the observable itself which can be used to further emit.
 */
private fun flatMapOperator(){
    val observable = Observable.interval(1,TimeUnit.SECONDS)
        .flatMap { Observable.just(it*10) }
        .subscribe { println(it) }

    Thread.sleep(5000)

}


/**
 * Returns an Observable that emits the items emitted by two ObservableSources,
 * one after the other, without interleaving them.
 */
private fun concatOperator(){
    val observable1 = Observable.just(1,2,3,4,5)
    val observable2 = Observable.just(6,7,8,9 ,20)

    Observable.concat(observable1,observable2)
        .subscribe { println(it) }
}


/**
 * Returns combined items emitted by multiple ObservableSources so that they appear as a single ObservableSource
 * but the order of the emissions is always the same.
 */
private fun concatWithOperator(){
    val observable1 = Observable.interval(1,TimeUnit.SECONDS)
        //.take(5)
        .map { "Observable 1 : $it" }
    val observable2 = Observable.interval(200,TimeUnit.MILLISECONDS)
        .map { "Observable 2 : $it" }

    observable1.concatWith(observable2)
        .subscribe { println(it) }

    Thread.sleep(10000)
}
