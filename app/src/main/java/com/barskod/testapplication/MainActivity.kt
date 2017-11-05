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

        textView.text = "0"
        button.setOnClickListener {
            textView.text = calc(editText.text.toString())
        }
    }

    /**
     * Calculate expression using Reverse Polish Notation
     */
    private fun calc(input: String): String {
        val rpn = invertStack(toRPN(input))
        val result = Stack()
        var msgError = ""

        try {
            while(!rpn.isEmpty()) {
                val item = rpn.pop()
                if(!item.isEmpty()) {
                    if(item[0].isDigit()) {
                        result.push(item)
                        continue
                    }

                    val num2 = result.pop().toDouble()
                    val num1 = result.pop().toDouble()

                    if(num2 == 0.0) {
                        msgError = resources.getString(R.string.msg_error_zero)
                        break
                    }

                    @Suppress("IMPLICIT_CAST_TO_ANY")
                    result.push(when(item[0]) {
                        '+' -> num1+num2
                        '-' -> num1-num2
                        '*' -> num1*num2
                        '/' -> num1/num2
                        '%' -> num1%num2
                        else -> 0
                    }.toString())
                }
            }
        } catch (e: Exception) {
            msgError = resources.getString(R.string.msg_error_unknown)
        }

        return if(msgError.isEmpty()) {
            result.pop()
        } else {
            msgError
        }
    }

    /**
     * Make input expression to Reverse Polish Notation
     */
    private fun toRPN(expr: String): Stack {
        val result = Stack()
        val stack = SymbolsStack()
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
                    val last = stack.pop()
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
        val result = Stack()
        while(!stack.isEmpty()) result.push(stack.pop())
        return result
    }
}