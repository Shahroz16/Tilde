package com.apps.tilde

import org.junit.Assert
import org.junit.Test

/**
 * Created by shahrozali on 5/19/17.
 */
class TildeTest {

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
    fun testBinarySearch() {
        val samplelist = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val result = _t.binarySearch(samplelist) {
            i ->
            i.compareTo(5)
        }

        Assert.assertEquals("Binary search test", 4, result);
    }

    @Test
    fun testExtensions() {
        val array = arrayListOf(1, 3, 2, 4, 5)
        val cycle = ArrayList<Int>()
        array.cycle(3) {
            cycle.add(it)
        }
        emptyArray<String>().forEach { }
        Assert.assertEquals("Cycle", arrayListOf(1, 3, 2, 4, 5, 1, 3, 2, 4, 5, 1, 3, 2, 4, 5), cycle)
        System.out.println(array.chunks(2))
    }

}