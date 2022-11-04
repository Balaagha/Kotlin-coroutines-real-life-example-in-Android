package com.example.androidimpltemplate.coroutine_console_examples

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main(){
    println("Program execution now blocked ")
    runBlocking {
        launch {
            delay(1000)
            println("Task from runBlocking ")
        }
        GlobalScope.launch {
            delay(500)
            println("Task from GlobalScope ")
        }

        coroutineScope {
            launch {
                delay(1500)
                println("Task from coroutineScope ")
            }
        }
    }
    println("Program execution will now continue")
}