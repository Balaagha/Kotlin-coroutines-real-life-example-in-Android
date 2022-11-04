package com.example.androidimpltemplate.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.view.isVisible
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentDownloadImageExampleBinding
import com.example.androidimpltemplate.utils.helper.Filter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

class DownloadImageExampleFragment :
    BaseFragment<FragmentDownloadImageExampleBinding>(FragmentDownloadImageExampleBinding::inflate) {

    private val IMAGE_URL =
        "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"

    private val coroutineScope by lazy { CoroutineScope(Dispatchers.Main) }

    override fun setup() {
        coroutineScope.launch {
            val originalDeferred = async(Dispatchers.IO) {
                getOriginalBitmap()
            }
            val originalBitmap = originalDeferred.await()

            val filteredDeferred = async(Dispatchers.Default) {
                applyFilter(originalBitmap)
            }
            val filteredBitmap = filteredDeferred.await()

            loadImageOnUi(filteredBitmap)
        }


    }

    private fun loadImageOnUi(bmp: Bitmap) {
        binding.apply {
            progressBar.isVisible = false
            imageView.setImageBitmap(bmp)
            imageView.isVisible = true
        }
    }

    private fun getOriginalBitmap(): Bitmap {
        return URL(IMAGE_URL).openStream().use {
            BitmapFactory.decodeStream(it)
        }
    }

    private fun applyFilter(bmp: Bitmap) = Filter.apply(bmp)

    companion object {
        @JvmStatic
        fun newInstance() = DownloadImageExampleFragment()
    }
}