package com.example.androidimpltemplate.ui

import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentExampleBinding

class ExampleFragment : BaseFragment<FragmentExampleBinding>(FragmentExampleBinding::inflate) {

    override fun setup() { }

    companion object {
        @JvmStatic
        fun newInstance() = ExampleFragment()
    }
}