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
     * Creates a function that, when called, invokes func with the binding of arguments provided.
     *
     * @param   function    Function to be bound.
     * @param   parameters  Parameters to be passed into the function when being invoked.
     * @return  A new function that when called will invoked the passed function with the parameters specified.
     */
    fun <T, E> bind(function: (Array<T>) -> E, parameters: Array<T>): () -> E {
        return {
            function(parameters)
        }
    }

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
     * Compose two or more functions passing result of the first function
     * into the next function until all the functions have been evaluated.
     *
     * @param   functions   list of functions.
     * @return  A function that can be called with variadic parameters of values
     */
    fun <T> compose(vararg functions: (Array<(Array<T>) -> Array<T>>) -> ((Array<T>) -> Array<T>)): Unit {
        TODO("")
//    open class func compose<T>(_ functions: ((T...) -> [T])...) -> ((T...) -> [T]) {
//        typealias Function = ([T]) -> [T]
//        return {
//            var result = $0
//            for fun in functions {
//                let f = unsafeBitCast(fun, to: Function.self)
//                result = f(result)
//            }
//            return result
//        }
//    }
    }

    fun <T> range(start: Int = 0, end: Int, inclusive: Boolean = true): IntRange {
        return start..if (inclusive) end else end - 1
    }

    /// Flattens a nested lists of any depth.
    ///
    /// - parameter array: The list to flatten.
    /// - returns: Flattened list.
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

    // Returns the collection wrapped in the chain object
    ///
    /// - parameter collection: of elements
    /// - returns: Chain object
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

    /// Get the first object in the wrapper object.
    ///
    /// - returns: First element from the list.
    fun first(): T {
        return value.first()
    }

    /// Get the last object in the wrapper object.
    ///
    /// - returns: Last element from the list.
    fun last(): T {
        return value.last()
    }

    /// Flattens nested lists.
    ///
    /// - returns: The wrapper object.
    fun flatten(): Chain<T> {
        return queue {
            return@queue Wrapper(_t.flatten(it.value, ArrayList()))
        }
    }

    /// Keeps all the elements except last one.
    ///
    /// - returns: The wrapper object.
    fun initial(): Chain<T> {
        return queue {
            return@queue Wrapper(it.value.dropLast(1))
        }
    }

    /// Keeps all the elements except last n elements.
    ///
    /// - parameter numElements: Number of items to remove from the end of the array.
    /// - returns: The wrapper object.
    fun initial(numElements: Int): Chain<T> {
        return queue {
            return@queue Wrapper(it.value.dropLast(numElements))
        }
    }

    /// Maps elements to new elements.
    ///
    /// - parameter function: Function to map.
    /// - returns: The wrapper object.
    fun map(function: (T) -> T): Chain<T> {
        return queue {
            return@queue Wrapper(it.value.map(function))
        }
    }

    /// Get the first object in the wrapper object.
    ///
    /// - parameter function: The array to wrap.
    /// - returns: The wrapper object.
    fun each(function: (T) -> Unit): Chain<T> {
        return queue {
            wrapper ->
            return@queue Wrapper(wrapper.value.onEach {
                function(it)
            }
            )
        }
    }

    /// Filter elements based on the function passed.
    ///
    /// - parameter function: Function to tell whether to keep an element or remove.
    /// - returns: The wrapper object.
    fun filter(function: (T) -> Boolean): Chain<T> {
        return queue {
            return@queue Wrapper(it.value.filter(function))
        }
    }

    /// Returns if all elements in array are true based on the passed function.
    ///
    /// - parameter function: Function to tell whether element value is true or false.
    /// - returns: Whether all elements are true according to func function.
    fun all(function: (T) -> Boolean): Boolean {
        return this.value.all(function)
    }

    /// Returns if any element in array is true based on the passed function.
    ///
    /// - parameter function: Function to tell whether element value is true or false.
    /// - returns: Whether any one element is true according to func function in the array.
    fun any(function: (T) -> Boolean): Boolean {
        return this.value.any(function)
    }

    /// Returns size of the array
    ///
    /// - returns: The wrapper object.
    fun size(): Int {
        return this.value.size
    }

    /// Slice the array into smaller size based on start and end value.
    ///
    /// - parameter start: Start index to start slicing from.
    /// - parameter end: End index to stop slicing to and not including element at that index.
    /// - returns: The wrapper object.
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