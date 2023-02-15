package com.kimoterru.noted.presenter.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kimoterru.noted.R
import com.kimoterru.noted.databinding.FragmentHomeBinding
import com.kimoterru.noted.presenter.util.addMenuProvider
import com.kimoterru.noted.presenter.util.setOnQueryListener

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeScreenSetup()
    }

    private fun homeScreenSetup() = with(binding) {
        addNewNoteView.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_home_to_fragment_new_note)
        }
        addMenu()
    }

    private fun addMenu() = addMenuProvider(
        menuRes = R.menu.menu_home,

        onCreateMenu = { menu ->
            val searchView = menu.findItem(R.id.action_search).actionView as SearchView
            searchView.setOnQueryListener {
              /* Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()*/
            }
        },

        onMenuItemSelected = {
            when (it.itemId) {
                R.id.action_sort -> findNavController().navigate(R.id.action_fragment_home_to_fragment_detail_note)
                android.R.id.home -> findNavController().popBackStack()
            }
        }
    )
}