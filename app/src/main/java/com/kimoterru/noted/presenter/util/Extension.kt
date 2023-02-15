package com.kimoterru.noted.presenter.util

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.MenuRes
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

typealias OnMenuItemSelected = (item: MenuItem) -> Unit
typealias OnCreateMenu = (menu: Menu) -> Unit
typealias OnPrepareMenu = (menu: Menu) -> Unit

fun Fragment.addMenuProvider(
    @MenuRes menuRes: Int,
    onMenuItemSelected: OnMenuItemSelected = {},
    onCreateMenu: OnCreateMenu = {},
    onPrepareMenu: OnPrepareMenu = {}
) {

    val menuProvider = object : MenuProvider {

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(menuRes, menu)
            onCreateMenu(menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            onMenuItemSelected(menuItem)
            return true
        }

        override fun onPrepareMenu(menu: Menu) {
            super.onPrepareMenu(menu)
            onPrepareMenu(menu)
        }

    }

    requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
}

typealias OnQueryListener = (text: String) -> Unit

fun SearchView.setOnQueryListener(onQueryListener: OnQueryListener) {

    isSubmitButtonEnabled = false

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?) = false

        override fun onQueryTextChange(newText: String?): Boolean {
            val text = newText ?: return false
            onQueryListener(text)
            return true
        }
    })
}