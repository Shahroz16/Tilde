package com.apps.tilde

/**
 * Created by rehan on 5/23/17.
 */

/**
 * Creates an list of elements from the specified indexes, or keys, of the collection.
 * Indexes may be specified as individual arguments or as arrays of indexes.
 *
 * @param   indexes Get elements from these indexes.
 * @return  New list with elements from the indexes specified.
 */
fun <T, E : List<T>> E.at(vararg indexes: Int): ArrayList<T> {
    return this.at(indexes.toList());
}

/**
 * Creates an list of elements from the specified indexes, or keys, of the collection.
 * Indexes may be specified as individual arguments or as arrays of indexes.
 *
 * @param   indexes Get elements from these indexes.
 * @return  New list with elements from the indexes specified.
 */
fun <T, E : List<T>> E.at(indexes: List<Int>): ArrayList<T> {
    return indexes.mapTo(ArrayList<T>()) { this[it] }
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

fun (() -> Boolean).loop(function: () -> Unit) {
    while (this()) {
        function()
    }
}

fun <T> ((T) -> Boolean).loop(function: () -> T) {
    while (this(function())) {
    }
}