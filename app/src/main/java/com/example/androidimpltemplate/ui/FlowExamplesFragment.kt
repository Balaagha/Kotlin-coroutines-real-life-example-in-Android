package com.example.androidimpltemplate.ui

import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentFlowExamplesBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class FlowExamplesFragment :
    BaseFragment<FragmentFlowExamplesBinding>(FragmentFlowExamplesBinding::inflate) {
    val jobList = arrayListOf<Job>()

    override fun setup() {
        binding.apply {

            createBasicFlow.setOnClickListener {
                ensureAllJobCanceled {
                    CoroutineScope(Dispatchers.IO).launch {
                        println("start numbers collection")
                        generateNumbers().collect {
                            println("number: $it")
                        }
                        println("finish numbers collection")

                        generateNumbersFromCollection().collect {

                        }
                    }
                }
            }

            createCollectionFlow.setOnClickListener {
                ensureAllJobCanceled {
                    CoroutineScope(Dispatchers.IO).launch {
                        println("start numbers collection")
                        generateNumbersFromCollection().collect {
                            println("number: $it")
                        }
                        println("finish numbers collection")
                    }
                }
            }

            flowWithCancellation.setOnClickListener {
                ensureAllJobCanceled {
                    CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
                        println("Exception is ${throwable.localizedMessage}")
                    }).launch {
                        withTimeout(1000L) {
                            generateNumbers().collect {
                                println("it -> $it")
                            }
                        }
                    }
                }
            }

            flowReduceOperatorBtn.setOnClickListener {
                ensureAllJobCanceled {
                    CoroutineScope(Dispatchers.IO).launch {
                        val timeAndResponseList: ArrayList<Pair<String, String>> = arrayListOf()
                        (1..100).forEach { mSize ->
                            val time: String
                            val factorialResult: String
                            time = measureTimeMillis {
                                factorialResult = calculateFactorial(mSize).toString()
                            }.toString()
                            timeAndResponseList.add(Pair(time, factorialResult))
                        }
                        timeAndResponseList.forEach {
                            println("time: ${it.first} | factorial result: ${it.second}")
                        }
                    }
                }
            }

        }


        binding.apply {
            flowBufferExampleBtn.setOnClickListener {
                ensureAllJobCanceled {
                    val withBuffer = Random.nextBoolean()
                    println("it runs ${if (withBuffer) "with" else "without"} buffer")
                    CoroutineScope(Dispatchers.IO).launch {
                        val measuredTime = measureTimeMillis {
                            generateNumbers(
                                size = 10,
                                delayedTime = 100,
                                isPrintEveryEmit = true
                            ).apply {
                                if(withBuffer) {
                                    this.buffer()
                                }
                                this.collect {
                                    delay(200)
                                    println("collected number: $it")
                                }
                            }
                        }
                        println("elapsed time: $measuredTime")
                    }
                }
            }
        }
    }

    // Example 1
    private fun generateNumbers(
        size: Int = 20,
        delayedTime: Long = 200L,
        isPrintEveryEmit: Boolean = false
    ): Flow<Int> = flow {
        for (i in 0..size) {
            delay(delayedTime)
            emit(i)
            if (isPrintEveryEmit) {
                println("Emit $i in generateNumbers")
            }
        }
    }

    // Example 2
    private fun generateNumbersFromCollection() =
        listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asFlow()

    // Example 3
    private suspend fun calculateFactorial(size: Int): Int {
        val factorial = (1..size).asFlow().reduce { accumulator, value -> accumulator * value }
//        println("size: $size | factorial: $factorial ")
        return factorial
    }

    // utils
    private fun ensureAllJobCanceled(mFunc: () -> Job) {
        with(jobList) {
            forEach {
                it.cancel()
                this.remove(it)
            }
            jobList.add(mFunc.invoke())
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = FlowExamplesFragment()
    }
}
