package com.example.androidimpltemplate.menu.view

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.databinding.FragmentMenuItemListBinding
import com.example.androidimpltemplate.menu.adapter.MenuItemsArrayAdapter
import com.example.androidimpltemplate.menu.itemsenum.MenuItemsEnum
import com.example.androidimpltemplate.menu.viewmodel.MenuViewModel
import com.example.androidimpltemplate.ui.DownloadImageExampleFragment
import com.example.androidimpltemplate.ui.MainActivity
import com.example.androidimpltemplate.utils.extentions.isNotNull
import com.example.androidimpltemplate.utils.extentions.navigateToTargetFragment

class MenuItemListFragment :
    BaseFragment<FragmentMenuItemListBinding>(FragmentMenuItemListBinding::inflate),
    MenuItemsArrayAdapter.Listener {

    private val viewModel by viewModels<MenuViewModel>() // activityViewModels<MenuViewModel>()

    override var statusBarVisibility: Boolean? = true

    private lateinit var mAdapterScreensReachableFromMenu: MenuItemsArrayAdapter

    override fun setup() {
        initMenuAdapter()

    }

    private fun initMenuAdapter() {
        mAdapterScreensReachableFromMenu =
            MenuItemsArrayAdapter(requireContext(), viewModel.getMenuList(), this)
        binding.listScreens.adapter = mAdapterScreensReachableFromMenu
    }

    override fun onMenuItemClicked(menuItem: MenuItemsEnum?) {
        when (menuItem) {
            MenuItemsEnum.MAIN_ACTIVITY -> {
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
            else -> {
                if (menuItem?.mNavId.isNotNull()) {
                    findNavController().navigate(menuItem?.mNavId!!)
                } else if (menuItem?.mFragmentInstance.isNotNull()) {
                    this.navigateToTargetFragment(
                        containerId = R.id.navHostFragment,
                        menuItem?.mFragmentInstance!!
                    )
                }
            }
        }


    }
}