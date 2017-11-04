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
        var rpn = invertStack(toRPN(input))
        var result = Stack()

        while(!rpn.isEmpty()) {
            var item = rpn.pop()
            if(!item.isEmpty()) {
                if(item[0].isDigit()) {
                    result.push(item)
                    continue
                }

                var num2 = result.pop().toInt()
                var num1 = result.pop().toInt()

                result.push(when(item[0]) {
                    '+' -> num1+num2
                    '-' -> num1-num2
                    '*' -> num1*num2
                    '/' -> num1/num2
                    else -> 0
                }.toString())
            }
        }

        return result.pop()
    }

    /* Make string expression to reverse polish notation */
    private fun toRPN(expr: String): Stack {
        var result = Stack()
        var stack = SymbolsStack()
        var buffer = ""

        for(ch: Char in expr) {
            if(ch.isDigit()) {
                buffer += ch.toString()
                continue
            }

            result.push(buffer)
            buffer = ""
            if(stack.isEmpty() || ch == '(') {
                stack.push(ch)
            } else if(ch == ')') {
                loop@while(!stack.isEmpty()) {
                    var last = stack.pop()
                    when(last) {
                        '(' -> break@loop
                        else -> result.push(last.toString())
                    }
                }
            } else {
                if(stack.comparePriority(ch)) {
                    stack.push(ch)
                } else {
                    while(!stack.isEmpty()) {
                        if(!stack.comparePriority(ch)) {
                            result.push(stack.pop().toString())
                        } else {
                            break
                        }
                    }
                    stack.push(ch)
                }
            }
        }

        result.push(buffer)
        while(!stack.isEmpty()) {
            result.push(stack.pop().toString())
        }
        return result
    }

    private fun invertStack(stack: Stack): Stack {
        var result = Stack()
        while(!stack.isEmpty()) result.push(stack.pop())
        return result
    }
}