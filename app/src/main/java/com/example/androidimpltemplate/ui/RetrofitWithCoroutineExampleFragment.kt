package com.example.androidimpltemplate.ui

import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentDownloadImageExampleBinding
import com.example.androidimpltemplate.databinding.FragmentRetrofitWithCoroutineExampleBinding

class RetrofitWithCoroutineExampleFragment :
    BaseFragment<FragmentRetrofitWithCoroutineExampleBinding>(FragmentRetrofitWithCoroutineExampleBinding::inflate) {

    override fun setup() {
    }
    companion object {
        @JvmStatic
        fun newInstance() = RetrofitWithCoroutineExampleFragment()
    }
}