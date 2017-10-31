package com.barskod.testapplication

/**
 * Created by vdkustov on 31.10.2017.
 */
open class Stack {

    private val SIZE = 255
    private var array = arrayOfNulls<Char>(SIZE)
    private var position = 0

    open fun push(item: Char) {
        array[position++] = item
    }

    open fun pop(): Char? {
        return array[position--]
    }

}