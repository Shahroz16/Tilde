package com.apps.tilde

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.concurrent.schedule

/**
 * Created by rehan on 5/23/17.
 */

/**
 * Executes the block on null value to return a non null value;
 * if the value is already assigned, returns it without executing the block
 *
 * @param   block   To generate new value if null
 * @return  Non null value
 */
inline fun <T> T?.assign(block: () -> T): T = this ?: block()

/**
 * Creates an list of elements from the specified indexes, or keys, of the collection.
 * Indexes may be specified as individual arguments or as arrays of indexes.
 *
 * @param   indexes Get elements from these indexes.
 * @return  New list with elements from the indexes specified.
 */
fun <T> Iterable<T>.at(vararg indexes: Int): ArrayList<T> {
    return this.at(indexes.toList())
}

/**
 * Creates an list of elements from the specified indexes, or keys, of the collection.
 * Indexes may be specified as individual arguments or as arrays of indexes.
 *
 * @param   indexes Get elements from these indexes.
 * @return  New list with elements from the indexes specified.
 */
fun <T> Iterable<T>.at(indexes: List<Int>): ArrayList<T> {
    return indexes.mapTo(ArrayList<T>()) { this.elementAt(it) }
}

/**
 * Creates an array of elements split into groups the length of size.
 * If array can’t be split evenly, the final chunk will be the remaining elements.
 *
 * @param   size    size of each chunk.
 * @return  array chunked elements.
 */
fun <T> Iterable<T>.chunks(size: Int = 1): ArrayList<ArrayList<T>> {
    val result = ArrayList<ArrayList<T>>()
    this.mapIndexed {
        index, item ->
        if (index % size == 0) {
            result.add(ArrayList<T>())
        }
        result.last().add(item)
    }
    return result
}

/**
 * Creates an array with all nil values removed.
 *
 * @return  A new array that doesnt have any nil values.
 */
fun <T> Iterable<T?>.compact(): ArrayList<T> {
    val result = ArrayList<T>()
    this.forEach {
        if (it != null) {
            result.add(it)
        }
    }
    return result
}

/**
 * Cycles through the array n times passing each element into the callback function
 * And indefinitely if n is -1
 *
 * @param   times       Number of times to cycle through the array
 * @param   callback    function to call with the element.
 */
fun <T, U> Iterable<T>.cycle(times: Int = -1, callback: (T) -> U) {
    val function = {
        this.forEach { callback(it) }
    }
    if (times == -1) {
        { true }.loop(function)
    } else {
        (1..times).forEach { function() }
    }
}

/**
 * Delays the execution of a function by the specified DispatchTimeInterval.
 *
 * @param   delay   interval to delay the execution of the function by.
 * @param   block   block to execute.
 */
inline fun <T> T.delay(delay: Long, crossinline block: T.() -> Any) {
    Timer().schedule(delay) {
        block()
    }
}

// debounce => Unimplemented

/**
 * Creates an array excluding all values of the provided arrays in order.
 *
 * @param   arrays  The arrays to difference between.
 * @return  The difference between the first array and all the remaining arrays from the arrays params.
 */
fun <T> Iterable<T>.differenceInOrder(vararg arrays: Iterable<T>): ArrayList<T> {
    val result = ArrayList<T>()
    this.filter {
        item ->
        arrays.forEach {
            array ->
            if (array.contains(item)) {
                return@filter false
            }
        }
        return@filter true
    }.forEach {
        result.add(it)
    }
    return result
}

/**
 * Returns Factorial of number.
 *
 * @receiver    Number whose factorial needs to be calculated.
 * @return      Factorial of the receiver.
 */
fun Int.factorial(): Int {
    return toLong().factorial().toInt()
}

/**
 * Returns Factorial of number.
 *
 * @receiver    Number whose factorial needs to be calculated.
 * @return      Factorial of the receiver.
 */
fun Long.factorial(): Long {
    if (this > 0) {
        return this * (this - 1L).factorial()
    } else {
        return 1L
    }
}

/**
 * This method returns a dictionary of values in an array mapping to the
 * total number of occurrences in the array. If passed a function it returns
 * a frequency table of the results of the given function on the arrays elements.
 *
 * @receiver    The array to source from.
 * @return      Dictionary that contains the key generated from the element passed in the function.
 */
fun <V> Iterable<V>.frequency(): HashMap<V, Int> {
    val map = HashMap<V, Int>()
    this.forEach {
        map[it] = map[it].assign { 0 } + 1
    }
    return map
}

/**
 * This method returns a dictionary of values in an array mapping to the
 * total number of occurrences in the array. If passed a function it returns
 * a frequency table of the results of the given function on the arrays elements.
 *
 * @param       keyGenerator    The function to get value of the key for each element to group by.
 * @receiver    The array to source from.
 * @return      Dictionary that contains the key generated from the element passed in the function.
 */
fun <K, V> Iterable<V>.frequency(keyGenerator: (V) -> K?): HashMap<K, Int> {
    val map = HashMap<K, Int>()
    this.forEach {
        keyGenerator(it)?.let {
            map[it] = map[it].assign { 0 } + 1
        }
    }
    return map
}

/**
 * Splits a collection into sets, grouped by the result of running each value through a callback.
 *
 * @param       keyGenerator    Function whose response will be used as the key to group.
 * @receiver    The array to group.
 * @return      Grouped collection.
 */
fun <K, V> Iterable<V>.groupBy(keyGenerator: (V) -> K?): HashMap<K, ArrayList<V>> {
    val hashMap = HashMap<K, ArrayList<V>>()
    this.forEach {
        value ->
        keyGenerator(value)?.let {
            hashMap[it].assign {
                hashMap[it] = ArrayList<V>()
                hashMap[it]!!
            }.add(value)
        }
    }
    return hashMap
}

/**
 * Unline groupBy method, it splits a collection into set of single value, grouped by the result of running each value through a callback.
 *
 * @param       keyGenerator        Function whose response will be used as the key to group.
 * @param       overrideExisting    If true, the previous value present against the key will be overrided; default false.
 * @receiver    The array to group.
 * @return      Grouped collection.
 */
fun <K, V> Iterable<V>.groupByUnit(overrideExisting: Boolean = false, keyGenerator: (V) -> K?): HashMap<K, V> {
    val hashMap = HashMap<K, V>()
    this.forEach {
        value ->
        keyGenerator(value)?.let {
            hashMap[it] = hashMap[it]?.let { if (overrideExisting) value else it } ?: value
        }
    }
    return hashMap
}

/**
 * Loops to the passed function as far as the caller function returns true
 *.
 * @param   function    Funtion to be executed in each iteration
 */
fun (() -> Boolean).loop(function: () -> Unit) {
    while (this()) {
        function()
    }
}

/**
 * Loops to the passed function as far as the caller function returns true based on value passed
 * to caller function returned by the passed function
 *
 * @param   function    Funtion to be executed in each iteration and return a value to pass to caller function
 */
fun <T> ((T) -> Boolean).loop(function: () -> T) {
    while (this(function())) {
    }
}