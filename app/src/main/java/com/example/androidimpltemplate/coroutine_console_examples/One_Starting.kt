package com.example.androidimpltemplate.coroutine_console_examples

import kotlinx.coroutines.*

fun main(){
    exampleOne()
    exampleTwo()
}

fun exampleTwo() {
    runBlocking {
        repeat(1000_000){
            launch {
                print(".")
            }
        }
    }
}

fun exampleOne(){
    GlobalScope.launch {
        delay(1000)
        println("World")
    }
    print("Hello ")
    Thread.sleep(2000)

}