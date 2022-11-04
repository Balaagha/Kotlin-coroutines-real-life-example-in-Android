package com.example.androidimpltemplate.menu.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.androidimpltemplate.base.BaseViewModel
import com.example.androidimpltemplate.menu.itemsenum.MenuItemsEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    fun getMenuList() = MenuItemsEnum.values()
}