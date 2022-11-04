package com.example.androidimpltemplate.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.androidimpltemplate.R
import com.example.androidimpltemplate.menu.itemsenum.MenuItemsEnum

class MenuItemsArrayAdapter(
    context: Context,
    values: Array<MenuItemsEnum>,
    private val mListener: Listener
) :
    ArrayAdapter<MenuItemsEnum>(context, 0, values) {

    interface Listener {
        fun onMenuItemClicked(menuItem: MenuItemsEnum?)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_menu_item, parent, false)

        val menuElement = getItem(position)

        // display menu item name
        val menuItemName = view.findViewById<TextView>(R.id.txt_menu_item_name)
        menuItemName.text = menuElement!!.getMenuItemName()

        // set click listener on individual item view
        view.setOnClickListener { mListener.onMenuItemClicked(menuElement) }
        return view
    }
}
