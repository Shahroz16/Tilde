package com.apps.tilde

import org.junit.Test

/**
 * Created by shahrozali on 5/19/17.
 */
class TildeTest {


    @Test
    fun testChainEach() {
        val chain = Chain(listOf("1", "2", "3"))
        val expectedResultList = arrayListOf("1", "2", "3")
        val testList = ArrayList<String>()
        chain.each {
            s ->
            s + "taco"
        }
//        Assert.assertEquals(expectedResultList, testList)

    }


//    @Test
//    fun testChaining(){
//        val chain = Chain(listOf("1", "2", "3"))
//        Assert.assertEquals(chain.first(), "1")
//
//        val expectedResultList = arrayListOf("1", "2", "3")
//        val testList = ArrayList<String>()
//        chain.each {
//            System.out.println(it)
//            testList.add(it)
//        }
//        Assert.assertEquals(expectedResultList,testList)
//
//        var chain1 = Chain(listOf(1,2,3,4))
//        Assert.assertTrue("Goes through each element in the list", chain1.all { i: Int -> i < 100 })
//
//        chain1 = _t.chain(listOf(10, 20, 30, 40, 50))
//        Assert.assertFalse("All elements are not less than 40", chain1.all { i: Int -> i < 40 })
//
//        chain1 = _t.chain(listOf(10, 20, 30, 40, 50))
//        Assert.assertTrue("At least one element is less than 40", chain1.any { i: Int -> i < 40 })
//
//        val elements = ArrayList<Int>()
//        chain1.slice(start = 0,end = 3).each { elements.add(it) }
//
////        Assert.assertEquals("Chained sliced",elements, arrayListOf(10,20,30,40))
//
//        val ambigiousList = listOf<Any>(listOf(listOf(1,2),3, listOf(listOf(4,5))))
//        val chain2 = _t.chain(ambigiousList)
//        Assert.assertEquals("Flatten and initial",chain2.flatten().initial(3), listOf(1,2,3))
//
//
//    }

}