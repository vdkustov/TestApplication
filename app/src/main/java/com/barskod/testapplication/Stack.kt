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
        return array[--position]
    }

    open fun comparePriority(op: Char): Boolean {
        return (opPriority(op) > opPriority(array[position-1]))
    }

    /* Get priority of operation */
    private fun opPriority(op: Char?): Int {
        return when (op) {
            '(' -> 0
            ')' -> 1
            '+' -> 2
            '-' -> 2
            '*' -> 3
            '/' -> 3
            else -> -1
        }
    }

    open fun isEmpty() = (position == 0)

}