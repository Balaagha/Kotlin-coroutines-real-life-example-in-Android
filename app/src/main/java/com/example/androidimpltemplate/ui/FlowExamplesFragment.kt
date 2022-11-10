package com.example.androidimpltemplate.ui

import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentFlowExamplesBinding
import com.example.androidimpltemplate.utils.helper.log
import com.example.androidimpltemplate.utils.helper.logI
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class FlowExamplesFragment :
    BaseFragment<FragmentFlowExamplesBinding>(FragmentFlowExamplesBinding::inflate) {

    val jobList = arrayListOf<Job>()

    val channel: Channel<Int> = Channel<Int>()

    override fun setup() {
        binding.apply {

            createBasicFlow.setOnClickListener {
                ensureAllJobCanceled {
                    CoroutineScope(Dispatchers.IO).launch {
                        "start numbers collection".logI()
                        generateNumbers().collect {
                            "number: $it".logI()
                        }
                        log("finish numbers collection")

                        generateNumbersFromCollection().collect {

                        }
                    }
                }
            }

            createCollectionFlow.setOnClickListener {
                ensureAllJobCanceled {
                    CoroutineScope(Dispatchers.IO).launch {
                        log("start numbers collection")
                        generateNumbersFromCollection().collect {
                            log("number: $it")
                        }
                        log("finish numbers collection")
                    }
                }
            }

            flowWithCancellation.setOnClickListener {
                ensureAllJobCanceled {
                    CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
                        log("Exception is ${throwable.localizedMessage}")
                    }).launch {
                        withTimeout(1000L) {
                            generateNumbers().collect {
                                log("it -> $it")
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
                            log("time: ${it.first} | factorial result: ${it.second}")
                        }
                    }
                }
            }

            flowBufferExampleBtn.setOnClickListener {
                ensureAllJobCanceled {
                    val withBuffer = Random.nextBoolean()
                    log("it runs ${if (withBuffer) "with" else "without"} buffer")
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
                                    log("collected number: $it")
                                }
                            }
                        }
                        log("elapsed time: $measuredTime")
                    }
                }
            }

            channelBasicExampleBtn.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    for (i in 1..10){
                        channel.send(i)
                    }
                    for (i in 1..10){
                        log(channel.receive().toString())
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
                log("Emit $i in generateNumbers")
            }
        }
    }

    // Example 2
    private fun generateNumbersFromCollection() =
        listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asFlow()

    // Example 3
    private suspend fun calculateFactorial(size: Int): Int {
        val factorial = (1..size).asFlow().reduce { accumulator, value -> accumulator * value }
//        log("size: $size | factorial: $factorial ")
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
