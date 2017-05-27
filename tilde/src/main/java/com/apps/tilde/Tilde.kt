package com.apps.tilde

/**
 * Created by shahrozali on 5/18/17.
 */

typealias _t = Tilde
const val TAG: String = "TILDE"

object Tilde {

    /**
     * Creates a function that executes passed function only after being called n times.
     *
     * @param   num   Number of times after which to call function.
     * @param   function  Function to be called that takes params.
     * @return  Function that can be called n times after which the callback function is called.
     */
    fun <T, E> after(num: Int, function: (Array<T>) -> E?): (Array<T>) -> E? {
        var counter = num
        return {
            params: Array<T> ->
            counter = -1
            if (counter <= 0) {
                function(params)
            }
            TODO("Write it later")
        }
    }

    /**
     * Creates a function that executes passed function only after being called n times.
     *
     * @param   num         Number of times after which to call function.
     * @param   function    Function to be called that does not take any params.
     * @return  Function that can be called n times after which the callback function is called.
     */
    fun <T> after(num: Int, function: () -> T): () -> T {
        TODO("Write it later")
//        val f = this.after(num) {
//            params ->
//            return@after function()
//        }
    }

    /**
     * Executes the block on null value to return a non null value;
     * if the value is already assigned, returns it without executing the block
     *
     * @param   obj     Receiver object
     * @param   block   To generate new value if null
     * @return  Non null value
     */
    inline fun <T> assign(obj: T, block: () -> T) = obj.assign(block)

    /**
     * Creates an list of elements from the specified indexes, or keys, of the collection.
     * Indexes may be specified as individual arguments or as arrays of indexes.
     *
     * @param   iterable    Receiver object
     * @param   indexes Get elements from these indexes.
     * @return  New list with elements from the indexes specified.
     */
    fun <T> at(iterable: Iterable<T>, vararg indexes: Int) = iterable.at(indexes.toList())

    /**
     * Creates an list of elements from the specified indexes, or keys, of the collection.
     * Indexes may be specified as individual arguments or as arrays of indexes.
     *
     * @param   iterable    Receiver object
     * @param   indexes Get elements from these indexes.
     * @return  New list with elements from the indexes specified.
     */
    fun <T> at(iterable: Iterable<T>, indexes: List<Int>) = iterable.at(indexes)

    /**
     * Creates a function that, when called, invokes func with the binding of arguments provided.
     *
     * @param   function    Function to be bound.
     * @param   parameters  Parameters to be passed into the function when being invoked.
     * @return  A new function that when called will invoked the passed function with the parameters specified.
     */
    fun <T, E> bind(function: (Array<T>) -> E, parameters: Array<T>): () -> E = {
        function(parameters)
    }

    /**
     * Creates an array of elements split into groups the length of size.
     * If array can’t be split evenly, the final chunk will be the remaining elements.
     *
     * @param   iterable    Receiver object
     * @param   size    size of each chunk.
     * @return  array chunked elements.
     */
    fun <T> chunks(iterable: Iterable<T>, size: Int = 1) = iterable.chunks(size)

    /**
     * Creates an array with all nil values removed.
     *
     * @param   iterable    Receiver object
     * @return  A new array that doesnt have any nil values.
     */
    fun <T> compact(iterable: Iterable<T>) = iterable.compact()

    /**
     * Create a copy of an List.
     *
     * @param   array   The List to copy.
     * @return  New copy of List.
     */
    fun <T> copy(list: List<T>): List<T> {
        val results = ArrayList<T>()
        list.mapTo(results) { it }
        return results
    }

    /**
     * Searches this list or its range for an element for which [comparison] function returns zero using the binary search algorithm.
     * The list is expected to be sorted into ascending order according to the provided [comparison],
     * otherwise the result is undefined.
     *
     * If the list contains multiple elements for which [comparison] returns zero, there is no guarantee which one will be found.
     *
     * @param comparison function that compares an element of the list with the element being searched.
     *
     * @return the index of the found element, if it is contained in the list within the specified range;
     * otherwise, the inverted insertion point `(-insertion point - 1)`.
     * The insertion point is defined as the index at which the element should be inserted,
     * so that the list (or the specified subrange of list) still remains sorted.
     */
    fun <T> binarySearch(list: List<T>, from: Int = 0, to: Int = list.size, comparison: (T) -> Int): Int {
        return list.binarySearch(fromIndex = from, toIndex = to) {
            comparison(it)
        }
    }

// compose => Unimplemented

    /**
     * Cycles through the array n times passing each element into the callback function
     * And indefinitely if n is -1
     *
     * @param   iterable    Receiver object
     * @param   times       Number of times to cycle through the array
     * @param   callback    function to call with the element.
     */
    fun <T, U> cycle(iterable: Iterable<T>, times: Int = -1, callback: (T) -> U) = iterable.cycle(times, callback)

    /**
     * Delays the execution of a function by the specified DispatchTimeInterval.
     *
     * @param   obj     Receiver object
     * @param   delay   interval to delay the execution of the function by.
     * @param   block   block to execute.
     */
    inline fun <T> delay(obj: T, delay: Long, crossinline block: T.() -> Any) = obj.delay(delay, block)

// debounce => Unimplemented

    /**
     * Creates an array excluding all values of the provided arrays in order.
     *
     * @param   iterable    Receiver object
     * @param   arrays      The arrays to difference between.
     * @return  The difference between the first array and all the remaining arrays from the arrays params.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> differenceInOrder(iterable: Iterable<T>, vararg arrays: Iterable<T>)
            = iterable.differenceInOrder(arrays as Iterable<T>)

    /**
     * Returns Factorial of number.
     *
     * @param   num    Number whose factorial needs to be calculated.
     * @return      Factorial of the receiver.
     */
    fun factorial(num: Int) = num.factorial()

    /**
     * Returns Factorial of number.
     *
     * @param   num    Number whose factorial needs to be calculated.
     * @return  Factorial of the receiver.
     */
    fun factorial(num: Long) = num.factorial()

    /**
     * This method returns a dictionary of values in an array mapping to the
     * total number of occurrences in the array. If passed a function it returns
     * a frequency table of the results of the given function on the arrays elements.
     *
     * @param   iterable    Receiver object
     * @return  Dictionary that contains the key generated from the element passed in the function.
     */
    fun <T> frequency(iterable: Iterable<T>): HashMap<T, Int> = iterable.frequency()

    /**
     * This method returns a dictionary of values in an array mapping to the
     * total number of occurrences in the array. If passed a function it returns
     * a frequency table of the results of the given function on the arrays elements.
     *
     * @param   iterable        Receiver object
     * @param   keyGenerator    The function to get value of the key for each element to group by.
     * @return  Dictionary that contains the key generated from the element passed in the function.
     */
    fun <K, V> frequency(iterator: Iterable<V>, keyGenerator: (V) -> K?): HashMap<K, Int>
            = iterator.frequency(keyGenerator)

    /**
     * Splits a collection into sets, grouped by the result of running each value through a callback.
     *
     * @param   iterable        Receiver object
     * @param   keyGenerator    Function whose response will be used as the key to group.
     * @return  Grouped collection.
     */
    fun <K, V> groupBy(iterable: Iterable<V>, keyGenerator: (V) -> K?): HashMap<K, ArrayList<V>>
            = iterable.groupBy(keyGenerator)

    /**
     * Unline groupBy method, it splits a collection into set of single value, grouped by the result of running each value through a callback.
     *
     * @param   iterable            Receiver object
     * @param   keyGenerator        Function whose response will be used as the key to group.
     * @param   overrideExisting    If true, the previous value present against the key will be overrided; default false.
     * @return  Grouped collection.
     */
    fun <K, V> Iterable<V>.groupByUnit(iterable: Iterable<V>, overrideExisting: Boolean = false, keyGenerator: (V) -> K?): HashMap<K, V>
            = iterable.groupByUnit(overrideExisting, keyGenerator)

    /**
     * Loops to the passed function as far as the caller function returns true
     *.
     * @param   predicate    Receiver object
     * @param   function    Function to be executed in each iteration
     */
    fun loop(predicate: (() -> Boolean), function: () -> Unit) = predicate.loop(function)

    /**
     * Loops to the passed function as far as the caller function returns true based on value passed
     * to caller function returned by the passed function
     *
     * @param   predicate    Receiver object
     * @param   function    Function to be executed in each iteration and return a value to pass to caller function
     */
    fun <T> loop(predicate: ((T) -> Boolean), function: () -> T) = predicate.loop(function)

    /**
     * Loops to the passed function as far as the caller function returns true based on value passed
     * to caller function returned by the passed function
     *
     * @param   range       Range to loop on
     * @param   function    Function to be executed in each iteration and return a value to pass to caller function
     */
    fun <T : Comparable<T>> loop(range: IntRange, function: () -> T) = range.forEach { function() }

    /**
     * Creates an integer range based on values passed
     *
     * @param   start           Range to start from (inclusive)
     * @param   end             Range to end on
     * @param   endInclusive    Flag to include or exclude end range
     * @return  Newly created range
     */
    fun <T> range(start: Int = 0, end: Int, endInclusive: Boolean = true) = start..if (endInclusive) end else end - 1


    /**
     * Accumulates value starting with the first element and applying [function] from left to right to current accumulator value and each element.
     *
     * @param list List to apply reduce on
     * @param function function that takes current accumulator, and the element itself
     * and calculates next accumulator value
     */
    fun <T> reduce(list: List<T>, function: (acc: T, T) -> T): T {
        return list.reduce { acc, t -> function(acc, t) }
    }

    /**
     * Accumulates value starting with the first element and applying [function] from left to right
     * to current accumulator value and each element with its index in the original collection.
     *
     * @param list List to apply reduce on
     * @param [function] function that takes the index of an element, current accumulator value
     * and the element itself and calculates the next accumulator value.
     */
    fun <T> reduceIndexed(list: List<T>, function: (index: Int, acc: T, T) -> T): T {
        return list.reduceIndexed { index, acc, t -> function(index, acc, t) }
    }

    /**
     * Removes items from an list.
     *
     * @param list The list to remove items from.
     * @param itemTobeRemoved items to be removed
     * @return list with items removed.
     */
    fun <T> remove(list: List<T>, vararg itemTobeRemoved: T): List<Any?> {
        return list.remove(itemTobeRemoved.toHashSet())
    }

    /**
     * Flattens a nested lists of any depth.
     *
     * @param   list        The list to flatten.
     * @return  Flattened list.
     */
    fun <T> flatten(list: List<T>, resultList: ArrayList<T>): ArrayList<T> {
        for (items in list) {
            when (items) {
                is List<*> -> {
                    flatten(items as List<T>, resultList)
                }
                else -> {
                    resultList.add(items)
                }
            }
        }
        return resultList
    }

    /**
     * Opposite of Initial, returns all but first n elements .
     *
     * @param list list to source from
     * @param numElements number of elements to exclude from the beginning.
     * @return The rest of the elements.
     */
    fun <T> rest(list: List<T>, numElements: Int) : List<T>{
        return list.dropLast(numElements)
    }

    /**
     * Returns the collection wrapped in the chain object
     *
     * @param   list collection: of elements
     * @return  Chain object.
     */

    fun <T> chain(list: List<T>): Chain<T> {
        return Chain(list)
    }

}

data class Wrapper<out T>(val value: T)

open class Chain<T>(collection: List<T>) {
    var result: Wrapper<List<T>>
    var funcQueue: ArrayList<(Wrapper<List<T>>) -> Wrapper<List<T>>> = ArrayList()

    val value: List<T>
        get() {
            var result: Wrapper<List<T>> = this.result
            for (function in this.funcQueue) {
                result = function(result)
            }
            return result.value
        }

    init {
        this.result = Wrapper(collection)
    }

    /**
     * Get the first object in the wrapper object.
     *
     * @return  First element from the list.
     */
    fun first(): T {
        return value.first()
    }

    /**
     * Get the last object in the wrapper object.
     *
     * @return  Last element from the list.
     */
    fun last(): T {
        return value.last()
    }

    /**
     * Flattens nested lists.
     *
     * @return  The wrapper object.
     */
    fun flatten(): Chain<T> {
        return queue {
            return@queue Wrapper(_t.flatten(it.value, ArrayList()))
        }
    }

    /**
     * Keeps all the elements except last one.
     *
     * @return  The wrapper object.
     */
    fun initial(): Chain<T> {
        return queue {
            return@queue Wrapper(it.value.dropLast(1))
        }
    }

    /**
     * Keeps all the elements except last n elements.
     *
     * @param numElements: Number of items to removeElementsAtIndexes from the end of the list.
     * @return  The wrapper object.
     */
    fun initial(numElements: Int): Chain<T> {
        return queue {
            return@queue Wrapper(it.value.dropLast(numElements))
        }
    }

    /**
     * Maps elements to new elements.
     *
     * @param function: Function to map.
     * @return  The wrapper object.
     */
    fun map(function: (T) -> T): Chain<T> {
        return queue {
            return@queue Wrapper(it.value.map(function))
        }
    }

    /**
     * Performs the given [function] on each element.
     *
     * @return  The wrapper object.
     */
    fun each(function: (T) -> Unit): Chain<T> {
        return queue {
            wrapper ->
            return@queue Wrapper(wrapper.value.onEach {
                function(it)
            }
            )
        }
    }

    /**
     * Filter elements based on the [function] passed.
     *
     * @return  The wrapper object.
     */
    fun filter(function: (T) -> Boolean): Chain<T> {
        return queue {
            return@queue Wrapper(it.value.filter(function))
        }
    }

    /**
     * Returns `true` if all elements match the given [function].
     */
    fun all(function: (T) -> Boolean): Boolean {
        return this.value.all(function)
    }

    /**
     * Returns `true` if at least one element matches the given [function].
     */
    fun any(function: (T) -> Boolean): Boolean {
        return this.value.any(function)
    }

    /**
     * Returns size of the list.
     *
     */
    fun size(): Int {
        return this.value.size
    }

    /**
     * Returns a view of the portion of this list between the specified [start] (inclusive) and [end] (exclusive).
     *
     * @return The wrapper object.
     */
    fun slice(start: Int, end: Int): Chain<T> {
        return queue {
            return@queue Wrapper(it.value.slice(IntRange(start, end)))
        }
    }


    fun queue(function: (Wrapper<List<T>>) -> Wrapper<List<T>>): Chain<T> {
        funcQueue.add(function)
        return this
    }

}