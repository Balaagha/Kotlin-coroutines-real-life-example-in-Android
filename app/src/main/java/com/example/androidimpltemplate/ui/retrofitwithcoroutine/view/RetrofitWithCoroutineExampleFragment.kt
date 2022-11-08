package com.example.androidimpltemplate.ui.retrofitwithcoroutine.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentRetrofitWithCoroutineExampleBinding
import com.example.androidimpltemplate.ui.retrofitwithcoroutine.adapter.CountryListAdapter
import com.example.androidimpltemplate.ui.retrofitwithcoroutine.viewmodel.ListViewModel

class RetrofitWithCoroutineExampleFragment :
    BaseFragment<FragmentRetrofitWithCoroutineExampleBinding>(
        FragmentRetrofitWithCoroutineExampleBinding::inflate
    ) {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[ListViewModel::class.java]
        viewModel.refresh()

        lifecycleScope.launchWhenResumed {
            observeViewModel()
        }

    }

    override fun setup() {
        binding.apply {
            countriesList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = countriesAdapter
            }
        }

    }

    fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                binding.countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            binding.listError.visibility = if (isError == "") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.countriesList.visibility = View.GONE
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = RetrofitWithCoroutineExampleFragment()
    }
}