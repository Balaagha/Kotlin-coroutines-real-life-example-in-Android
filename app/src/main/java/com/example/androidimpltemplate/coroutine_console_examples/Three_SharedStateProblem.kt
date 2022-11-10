package com.example.androidimpltemplate.coroutine_console_examples

import com.example.androidimpltemplate.utils.helper.cLog
import com.example.androidimpltemplate.utils.helper.cLogI
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main(){
    runBlocking {
//        problemVersion()
//        firstSolution()
        secondSolution()
    }
}

suspend fun problemVersion(){
    var counter = 0
    withContext(Dispatchers.Default){
        massiveRun {
            counter++
        }
    }
    cLog("counter is $counter")
}

suspend fun firstSolution(){
    val counter = AtomicInteger(0)
    withContext(Dispatchers.Default){
        massiveRun {
            counter.incrementAndGet()
        }
    }
    cLog("counter is ${counter.get()}")
}

// Thread

@OptIn(ObsoleteCoroutinesApi::class)
suspend fun secondSolution(){
    val coroutineContext = newSingleThreadContext("CoroutineContext")

    var counter = 0
    withContext(Dispatchers.Default){
        massiveRunThreads {
            withContext(coroutineContext){
                counter++
            }
        }
    }
    cLog("counter is $counter")
}

suspend fun massiveRunThreads(action: suspend ()->Unit){
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n){
                launch {
                    repeat(k){
                        action()
                    }
                }
            }
        }
    }
    "$time time escaped".cLogI()
}
suspend fun massiveRun(action: ()->Unit){
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n){
                launch {
                    repeat(k){
                        action()
                    }
                }
            }
        }
    }
    "$time time escaped".cLogI()
}