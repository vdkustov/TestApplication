package com.barskod.testapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by vdkustov on 31.10.2017.
 */

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
        var stack = Stack()

        for(ch: Char in exp) {
            if(ch.isDigit()) {
                result += ch
                continue
            }

            if(stack.isEmpty() || ch == '(') {
                stack.push(ch)
            } else if(ch == ')') {
                loop@while(!stack.isEmpty()) {
                    var last = stack.pop()
                    when(last) {
                        '(' -> break@loop
                        else -> result += last
                    }
                }
            } else {
                if(stack.comparePriority(ch)) {
                    stack.push(ch)
                } else {
                    while(!stack.isEmpty()) {
                        if(!stack.comparePriority(ch)) {
                            result += stack.pop()
                        }
                    }
                    stack.push(ch)
                }
            }
        }

        while(!stack.isEmpty()) {
            result += stack.pop()
        }
        return result
    }
}