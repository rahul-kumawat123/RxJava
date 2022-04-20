package com.example.rxjava

import android.os.Build
import androidx.annotation.RequiresApi
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.N)
fun main (args: Array <String>){
    //mapOperator()
    //filterOperator()
   // mapAndFilterOperator()
  //  takeOperator()
  //  takeWhileOperator()
  //  skipOperator()
   // skipWhileOperator()
   // distinctOperator()
   // defaultIfEmptyOperator()
 //   switchIfEmptyOperator()
   // repeatOperator()
   // scanOperator()
   // sortedOperator()
    containsOperator()
}

/**
 * The Map operator applies a function of your choosing to each item emitted by the source Observable,
 * and returns an Observable that emits the results of these function applications.
 */
private fun mapOperator(){
    println("MAP OPERATOR")
    val observable = Observable.just(1,2,3,4)
        .map { it*5 }
        .subscribe { println(it) }


    // map operator can also return different data

   /* val observable = Observable.just(1,2,3,4)
        .map { "Hello World" }
        .subscribe { println(it) }*/
}


/**
 * This operator is used to filter out items that conform to a certain predicate.
 * It will emit only those items which satisfy the given condition.
 */
private fun filterOperator(){
    println("FILTER OPERATOR")

    val observable = Observable.just(1,2,3,4,5,6)
        .filter { it%2 ==0 }
        .subscribe { println(it) }
}


/**
 * We can also combine multiple operators to an observable
 */
private fun mapAndFilterOperator(){
    println("MAP AND FILTER OPERATOR")

    val observable = Observable.just(1,2,3,4,5,6)
        .filter { it % 2 ==0 }
        .map { it * 2 }
        .subscribe { println(it) }
}


/**
 *  Take filters the logic results in the emission of the first n items while ignoring the remaining items.
 */
private fun takeOperator(){
    println("TAKE OPERATOR")

    val observable = Observable.just(1,2,3,5,6,7,57,774,6)
        .take(3)
        .subscribe{ println(it)}

    //takeWithInterval

    /*val observable = Observable.interval(400,TimeUnit.MILLISECONDS)
        .take(2,TimeUnit.SECONDS)
        .subscribe{ println(it)}

    Thread.sleep(5000)*/
}

/**
 * takeWhile will keep emitting items until it encounters a first element that doesn't match the Predicate.
 */
private fun takeWhileOperator(){
    println("TAKEWHILE OPERATOR")

    val observable = Observable.just(1,2,3,5,6,7,57,774,6)
        .takeWhile { it <= 5 }
        .subscribe{ println(it)}
}


/**
 * Skip is used to filter out and skip some of the first n items
 */
private fun skipOperator(){
    println("SKIP OPERATOR")

    val observable = Observable.just(1,2,3,5,6,7,57,774,6)
        .skip(3)
        .subscribe{ println(it)}
}


/**
 *  SkipWhile operator will filter out all the first values emitted by an Observable that fail a filtering predicate
 */
private fun skipWhileOperator(){
    println("SKIPWHILE OPERATOR")

    val observable = Observable.just(1,2,3,5,6,7,57,774,6)
        .skipWhile { it <=3 }
        .subscribe{ println(it)}
}


/**
 * Used to emit those items only once if they are repeated more than once
 */
private fun distinctOperator(){
    println("DISTINCT OPERATOR")

    val observable = Observable.just(1,2,3,5,1,2,6,7,5,3,)
        .distinct()
        .subscribe{ println(it)}


    //another usage

   /* val observable = Observable.just("abs","jhge", "kjad", "aaaaa" , "hskas" , "asdd")
        .distinct { it.length }
        .subscribe { println(it) }*/
}

/**
 * Returns a defaultItem when condition is not satisfied
 */
private fun defaultIfEmptyOperator(){
    println("DEFAULTIFEMPTY OPERATOR")

    val observable = Observable.just(1,2,3,5,1,2,6,7,5,3)
        .filter { it >8 }
        .defaultIfEmpty(200)
        .subscribe{ println(it)}
}


/**
 * Returns a new Observable when condition is not satisfied
 */
private fun switchIfEmptyOperator(){
    println("SWITCHTIFEMPTY OPERATOR")

    val observable = Observable.just(1,2,3,5,1,2,6,7,5,3)
        .filter { it >8 }
        .switchIfEmpty ( Observable.just(7,5,6,8,10,34,67) )
        .subscribe{ println(it)}
}



/**
 * Repeats the observable n no. of times
 */
private fun repeatOperator(){
    println("REPEAT OPERATOR")

    val observable = Observable.just(1,2,3,5)
        .repeat(2)
        .subscribe{ println(it)}
}


/**
 * The scan() operator applies a function to the first item emitted by the Observable
 * and then emits the result of the function as its own first emission.
 */
private fun scanOperator(){
    println("SCAN OPERATOR")

    val observable = Observable.just(1,2,3,4,5,6)
        .scan { t1, t2 -> t1*t2 }
        .subscribe { println(it) }


    // scan with initial value

    /*val observable = Observable.just(1,2,3,4,5,6)
        .scan(10,{t1 , t2 -> t1+t2})
        .subscribe { println(it) }*/
}


/**
 * Returns a sorted observable as per the user specifications
 */
@RequiresApi(Build.VERSION_CODES.N)
private fun sortedOperator(){
    println("SORTED OPERATOR")

    val observable = Observable.just(11,13,9,17,4,16)
        .sorted()
        //.sorted(reverseOrder())
        .subscribe { println(it) }


   /* val observable = Observable.just("doo", "good", "super" , "hey", "water")
        .sorted(Comparator.comparingInt(String::length))
        .subscribe{ println(it)}*/
}


/**
 * Contains determine whether an Observable emits a particular item or not
 * It returns a Single instance
 */
private fun containsOperator(){
    println("CONTAINS OPERATOR")

    // contains with primitive data

    val observable = Observable.just(1,2,3,45)
        .contains(4)
        .subscribe { item -> println(item) }


    // contains with non-primitive data

    class Name(n: String) {}

    /*val n = Name("hello")
    val observable = Observable.just(n)
        .contains(n)
        .contains(Name("hello"))
        .subscribe { item -> println(item) }*/
}


