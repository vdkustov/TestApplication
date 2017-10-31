package com.barskod.testapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            textView.text = calc(editText.text.toString())
        }
    }

    /* Calculate expression */
    private fun calc(input: String): String {
        var notation = toReversePolishNotation(input)
        var output = notation
        for (i in 0..notation?.length) {

        }
        return output
    }

    /* Make string expression to reverse polish notation */
    private fun toReversePolishNotation(exp: String): String {
        var result = ""
        var length = exp.length-1
        var stack = Stack()

        (0..length)
                .map { exp[it] }
                .forEach {
                    if(it.isDigit()) {
                        result += it
                    } else {
                        if(operationPriority(it) == 2) {
                            stack.push(it)
                        }
                    }
                }
        return result
    }

    /*
    * Если это число, то кладём его на выход.
    * Если это операция, то {
    *   рр
    * }
    * */
    /* Get priority of operation */
    private fun operationPriority(op: Char): Int {
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

}