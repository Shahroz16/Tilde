package com.apps.tilde

import org.junit.Assert
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

/**
 * Created by shahrozali on 5/19/17.
 */
class TildeTest {

    var timeInNano: Long = 0
    lateinit var identifier: String
    lateinit var expected: Any
    lateinit var actual: Any

    fun startStopwatch() {
        timeInNano = System.nanoTime()
    }

    fun stopStopwatch() {
        System.out.println("${identifier} => ${TimeUnit.MICROSECONDS.convert(System.nanoTime() - timeInNano, TimeUnit.NANOSECONDS)} ${TimeUnit.MICROSECONDS.name}")
    }

    fun testChaining() {
        val chain = Chain(listOf("1", "2", "3"))
        Assert.assertEquals(chain.first(), "1")

        val expectedResultList = arrayListOf("1", "2", "3")
        val testList = ArrayList<String>()
        chain.each { s -> testList.add(s) }.value
        Assert.assertEquals(expectedResultList, testList)

        var chain1 = Chain(listOf(1, 2, 3, 4))
        Assert.assertTrue("Goes through each element in the list", chain1.all { i: Int -> i < 100 })

        chain1 = _t.chain(listOf(10, 20, 30, 40, 50))
        Assert.assertFalse("All elements are not less than 40", chain1.all { i: Int -> i < 40 })

        chain1 = _t.chain(listOf(10, 20, 30, 40, 50))
        Assert.assertTrue("At least one element is less than 40", chain1.any { i: Int -> i < 40 })

        val elements = ArrayList<Int>()
        chain1.slice(start = 0, end = 3).each { elements.add(it) }.value

        Assert.assertEquals("Chained sliced", elements, arrayListOf(10, 20, 30, 40))

        val ambigiousList = listOf<Any>(listOf(listOf(1, 2), 3, listOf(listOf(4, 5))))
        val chain2 = _t.chain(ambigiousList)
        Assert.assertEquals("Flatten and initial", chain2.flatten().initial(2).value, listOf(1, 2, 3))

    }

    fun testBinarySearch() {
        val samplelist = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val result = _t.binarySearch(samplelist) {
            i ->
            i.compareTo(5)
        }

        Assert.assertEquals("Binary search test", 4, result);
    }

    fun testRange() {
        val result = _t.range(end = 3)
        val result1 = _t.range(start = 1, end = 5, endInclusive = false)
        val result2 = _t.range(start = 1, end = 5, endInclusive = true)
        val result4 = _t.range(start = 0, end = 20, step = 5)

        Assert.assertEquals("Range check", listOf(0, 1, 2, 3), result)
        Assert.assertEquals("Range check1", listOf(1, 2, 3, 4), result1)
        Assert.assertEquals("Range check2", listOf(1, 2, 3, 4, 5), result2)
        Assert.assertEquals("Range check3", listOf(0, 5, 10, 15, 20), result4)
    }

    fun testReduce() {
        val list = listOf("a", "-small", "-cow")
        val expectedResponse = "a-small-cow"
        val expectedResponse1 = "ab1c2"

        val list2 = listOf("a", "b", "c")

        val result = _t.reduce(list) {
            s, t ->
            s + t
        }

        val result1 = _t.reduceIndexed(list2) {
            index: Int, acc: String, s: String ->
            acc + s + index
        }

        Assert.assertEquals("Reduce check", expectedResponse, result)
        Assert.assertEquals("Reduce indexed check", expectedResponse1, result1)
    }

    fun testRemove() {
        val list = listOf("a", "b", "c", "d", "e", "f")
        val result = list.removeElementsAtIndexes(0, 2, 4, 5)
        val expectedResponse = listOf("b", "d")

        val result1 = _t.remove(list, "a", "b", "f")
        val expectedResponse1 = listOf("c", "d", "e")

        Assert.assertEquals("Remove index check", expectedResponse, result)
        Assert.assertEquals("Remove check", expectedResponse1, result1)
        Assert.assertEquals("Remove check", expectedResponse1, list.remove("a", "b", "f"))

    }

    fun testRest() {
        val list = listOf("a", "b", "c", "d", "e", "f", "g")
        val result = _t.rest(list, 3)
        val expectedResponse = listOf("a", "b", "c", "d")

        Assert.assertEquals("Rest check", expectedResponse, result)

    }

    fun testExtensions() {
        testAssign()
        testAtVarargs()
        testAtList()
        testChunks()
        testCompact()
        testCycle()
        testDelay()
        testDifference()
        testFactorial()
        testFrequency()
        testGroupBy()
        testGroupByUnit()
        testLoopDependent()
        testLoopIndependent()
    }

    fun testAssign() {
        identifier = "Assign"

        expected = true
        startStopwatch()
        var actual = (null as Boolean?).assign {
            true
        }
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)

        expected = false
        startStopwatch()
        actual = false.assign {
            true
        }
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testAtVarargs() {
        identifier = "At varargs"
        val data = arrayListOf(1, 3, 2, 4, 5)

        expected = arrayListOf(3, 4, 5)
        startStopwatch()
        actual = data.at(1, 3, 4)
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testAtList() {
        identifier = "At list"
        val data = arrayListOf(1, 3, 2, 4, 5)

        expected = arrayListOf(1, 2, 3)
        startStopwatch()
        actual = data.at(listOf(0, 2, 1))
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testChunks() {
        identifier = "Chunks"
        val data = arrayListOf(1, 3, 2, 4, 5)

        expected = arrayListOf(arrayListOf(1, 3, 2), arrayListOf(4, 5))
        startStopwatch()
        actual = data.chunks(3)
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testCompact() {
        identifier = "Compact"
        val data = arrayListOf(null, "apple", "avocado", null, "banana", null, "coconut", null, null, "guava")

        expected = arrayListOf("apple", "avocado", "banana", "coconut", "guava")
        startStopwatch()
        actual = data.compact()
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testCycle() {
        identifier = "Cycle"
        val data = arrayListOf(1, 3, 2, 4, 5)

        expected = arrayListOf(1, 3, 2, 4, 5, 1, 3, 2, 4, 5, 1, 3, 2, 4, 5)
        val cycle = ArrayList<Int>()
        startStopwatch()
        data.cycle(3) {
            cycle.add(it)
        }
        stopStopwatch()
        actual = cycle
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testDelay() {
        identifier = "Delay"
        val data = arrayListOf(1, 3, 2, 4, 5)

        expected = data
        val delay = 1000L
        startStopwatch()
        data.delay(delay) {
            stopStopwatch()
            val endTime = System.nanoTime()
            val seconds = TimeUnit.SECONDS.convert(endTime - timeInNano, TimeUnit.NANOSECONDS)
            val diff = delay % 1000
            actual = this
            Assert.assertTrue(identifier, seconds in diff..(diff + 1))
            Assert.assertEquals(identifier, expected, actual)
        }
        Thread.sleep(delay * 2L)
    }

    fun testDifference() {
        identifier = "Difference"
        val data = arrayListOf(1, 3, 2, 4, 5)

        expected = arrayListOf(1, 2, 4)
        startStopwatch()
        actual = data.differenceInOrder(listOf(3, 7, 11, 13), listOf(5, 7, 11, 13))
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testFactorial() {
        identifier = "Factorial"

        expected = 24
        startStopwatch()
        actual = 4.factorial()
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)

    }

    fun testFrequency() {
        identifier = "Frequency"
        val data1 = arrayListOf(1, 2, 3, 2, 1, 1, 2, 2)

        expected = mapOf(1 to 3, 2 to 4, 3 to 1)
        startStopwatch()
        actual = data1.frequency()
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)

        val data2 = arrayListOf(null, "apple", "avocado", null, "banana", null, "coconut", null, null, "guava")
        expected = mapOf("a" to 4, "!a" to 1)
        startStopwatch()
        actual = data2.frequency {
            it?.let {
                if (it.contains('a')) {
                    return@frequency "a"
                } else {
                    return@frequency "!a"
                }
            }
        }
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testGroupBy() {
        identifier = "GroupBy"
        val data = arrayListOf(null, "apple", "avocado", null, "banana", null, "coconut", null, null, "guava")

        expected = mapOf('a' to arrayListOf("apple", "avocado"), 'b' to arrayListOf("banana"), 'c' to arrayListOf("coconut"), 'g' to arrayListOf("guava"))
        startStopwatch()
        actual = data.groupBy { it?.elementAt(0) }
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)

    }

    fun testGroupByUnit() {
        identifier = "GroupByUnit"
        val data = arrayListOf(null, "apple", "avocado", null, "banana", null, "coconut", null, null, "guava")

        expected = mapOf('a' to "apple", 'b' to "banana", 'c' to "coconut", 'g' to "guava")
        startStopwatch()
        actual = data.groupByUnit { it?.elementAt(0) }
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)

        expected = mapOf('a' to "avocado", 'b' to "banana", 'c' to "coconut", 'g' to "guava")
        startStopwatch()
        actual = data.groupByUnit(true) { it?.elementAt(0) }
        stopStopwatch()
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testLoopIndependent() {
        identifier = "Loop Independent"

        expected = 10
        startStopwatch()
        var number = 0
        { number < 10 }.loop { number++ }
        stopStopwatch()
        actual = number
        Assert.assertEquals(identifier, expected, actual)
    }

    fun testLoopDependent() {
        identifier = "Loop Dependent"

        var number: Int = 0
        expected = 0
        startStopwatch()
        val random = Random(System.currentTimeMillis());
        {
            n: Int ->
            n % 3 == 0
        }.loop {
            number = random.nextInt()
            number
        }
        stopStopwatch()
        actual = number % 3
        Assert.assertNotEquals(identifier, expected, actual)
    }


}