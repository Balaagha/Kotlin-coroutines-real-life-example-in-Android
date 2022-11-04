package com.example.androidimpltemplate.menu.itemsenum

import com.example.androidimpltemplate.base.BaseFragment
import com.example.androidimpltemplate.ui.DownloadImageExampleFragment
import com.example.androidimpltemplate.ui.RetrofitWithCoroutineExampleFragment

enum class MenuItemsEnum(name: String? = null, fragmentInstance: BaseFragment<*>?=null, navId: Int? = null) {

    MAIN_ACTIVITY("Main Activity Example"),
    DOWNLOAD_IMAGE_EXAMPLE_FRAGMENT(name = "Download image example", fragmentInstance = DownloadImageExampleFragment.newInstance()),
    RETROFIT_WITH_COROUTINE_EXAMPLE( fragmentInstance = RetrofitWithCoroutineExampleFragment.newInstance()),
    ;


    val mName: String?
    val mFragmentInstance: BaseFragment<*>?
    val mNavId: Int?

    init {
        mName = name
        mFragmentInstance = fragmentInstance
        mNavId =  navId
    }

}
