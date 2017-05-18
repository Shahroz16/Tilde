package com.apps.tilde

/**
 * Created by shahrozali on 5/18/17.
 */

typealias _t = Tilde
const val TAG : String = "TILDE"
object Tilde{

    /// Creates an array of elements from the specified indexes, or keys, of the collection.
    /// Indexes may be specified as individual arguments or as arrays of indexes.
    ///
    /// - parameter array: The array to source from
    /// - parameter indexes: Get elements from these indexes
    /// - returns: New array with elements from the indexes specified.
    fun <T> at(array : List<T>, vararg indexes : Int) : List<T>{
        val results = ArrayList<T>()
        indexes.mapTo(results) { array[it] }
        return results;
    }


}