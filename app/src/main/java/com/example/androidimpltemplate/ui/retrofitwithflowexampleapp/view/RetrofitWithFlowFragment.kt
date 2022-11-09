package com.example.androidimpltemplate.ui.retrofitwithflowexampleapp.view

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentRetrofitWithFlowBinding
import com.example.androidimpltemplate.ui.retrofitwithflowexampleapp.adapter.NewsListAdapter
import com.example.androidimpltemplate.ui.retrofitwithflowexampleapp.viewmodel.ListViewModel

class RetrofitWithFlowFragment : BaseFragment<FragmentRetrofitWithFlowBinding>(FragmentRetrofitWithFlowBinding::inflate) {

    lateinit var viewModel: ListViewModel
    private val newsListAdapter = NewsListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[ListViewModel::class.java]

        lifecycleScope.launchWhenResumed {
            observeViewModel()
        }

    }

    override fun setup() {
        binding.newsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.newsArticles.observe(this, Observer { article ->
            binding.apply {
                loadingView.isVisible = false
                newsList.isVisible = true

                newsListAdapter.onAddNewsItem(article)
                newsList.smoothScrollToPosition(0)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = RetrofitWithFlowFragment()
    }
}
