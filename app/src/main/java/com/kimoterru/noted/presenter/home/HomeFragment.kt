package com.kimoterru.noted.presenter.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kimoterru.noted.R
import com.kimoterru.noted.databinding.FragmentHomeBinding
import com.kimoterru.noted.presenter.util.NoteClickInterface
import com.kimoterru.noted.presenter.util.addMenuProvider
import com.kimoterru.noted.presenter.util.setOnQueryListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment(R.layout.fragment_home), NoteClickInterface {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModel()
    private val homeAdapter by lazy {
        HomeAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeScreenSetup()
    }

    private fun homeScreenSetup() = with(binding) {
        addNewNoteView.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_home_to_fragment_new_note)
        }
        notesRecyclerView.apply {
            adapter = homeAdapter
        }
        addMenu()
        initObservers()
    }

    private fun initObservers() = lifecycleScope.launch {
        viewModel.getListNotes().collectLatest {
            homeAdapter.submitData(lifecycle, it)
        }
    }

    private fun addMenu() = addMenuProvider(
        menuRes = R.menu.menu_home,

        onCreateMenu = { menu ->
            val searchView = menu.findItem(R.id.action_search).actionView as SearchView
            searchView.setOnQueryListener {
              /* Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()*/ // TODO: fix this is moment 
            }
        },

        onMenuItemSelected = {
            when (it.itemId) {
                R.id.action_sort -> Toast.makeText(context, getText(R.string.sort_by), Toast.LENGTH_SHORT).show()
                android.R.id.home -> findNavController().popBackStack()
            }
        }
    )

    override fun clickOnNote(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToFragmentDetailNote(idNote = id))
    }
}