package com.example.androidimpltemplate.menu.view

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentMenuSplashScreenBinding
import kotlinx.android.synthetic.main.fragment_menu_splash_screen.*

@SuppressLint("CustomSplashScreen")
class MenuSplashScreenFragment: BaseFragment<FragmentMenuSplashScreenBinding>(FragmentMenuSplashScreenBinding::inflate) {

    override var statusBarVisibility: Boolean? = false

    override fun setup() {

        val string = SpannableStringBuilder()
            .color(ContextCompat.getColor(requireContext(), R.color.white )) {
                append("Be ")
            }
            .color(ContextCompat.getColor(requireContext(), R.color.red_01)) {
                append("Tech")
            }
        tvTitle.text = string

        binding.apply {
            lottieAnimView.animate().apply {
                translationY(-1500F)
                duration = 750
                startDelay = 3000
            }
            tvTitle.animate().apply {
                alpha(0F)
                duration = 300
                startDelay = 3400
            }
            tvSubTitle.animate().apply {
                alpha(0F)
                duration = 300
                startDelay = 3600
                withEndAction {
                    viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                        findNavController().navigate(
                            R.id.menuItemListFragment,
                            null,
                            NavOptions.Builder()
                            .setPopUpTo(R.id.menuSplashScreenFragment, true)
                            .build()
                        )
                    }
                }
            }
        }
    }

}