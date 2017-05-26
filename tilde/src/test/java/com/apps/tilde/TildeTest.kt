package com.apps.tilde

import org.junit.Assert
import org.junit.Test
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

    @Test
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

    @Test
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

        var number : Int = 0
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