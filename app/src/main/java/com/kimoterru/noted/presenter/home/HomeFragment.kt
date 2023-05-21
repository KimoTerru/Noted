package com.kimoterru.noted.presenter.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kimoterru.noted.R
import com.kimoterru.noted.databinding.FragmentHomeBinding
import com.kimoterru.noted.presenter.util.NoteClickInterface
import com.kimoterru.noted.presenter.util.SORT_BY_NEW_NOTES
import com.kimoterru.noted.presenter.util.SORT_BY_OLD_NOTES
import com.kimoterru.noted.presenter.util.addMenuProvider
import com.kimoterru.noted.presenter.util.gone
import com.kimoterru.noted.presenter.util.setOnQueryListener
import com.kimoterru.noted.presenter.util.visible
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
        val sGrid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        sGrid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        notesRecyclerView.apply {
            layoutManager = sGrid
            adapter = homeAdapter
        }
        addMenu()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        homeAdapter.addLoadStateListener {
            if (it.append is LoadState.NotLoading && it.append.endOfPaginationReached && homeAdapter.itemCount < 1) {
                binding.notesRecyclerView.gone()
                binding.emptyAnim.visible()
            } else {
                binding.notesRecyclerView.visible()
                binding.emptyAnim.gone()
            }
        }
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
              viewModel.searchWordInNote(it)
                homeAdapter.refresh()
            }
        },

        onMenuItemSelected = {
            when (it.itemId) {
                R.id.action_sort_first_old -> {
                    viewModel.sortByNotes(SORT_BY_OLD_NOTES)
                    homeAdapter.refresh()
                }
                R.id.action_sort_first_new -> {
                    viewModel.sortByNotes(SORT_BY_NEW_NOTES)
                    homeAdapter.refresh()
                }
                R.id.action_delete_all -> {
                    makeSure()
                }
                android.R.id.home -> findNavController().popBackStack()
            }
        }
    )

    private fun makeSure() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(getText(R.string.requst_to_delete))
            .setNegativeButton(getText(R.string.no)) { dialog, _ -> dialog.dismiss() }
            .setPositiveButton(getText(R.string.yes)) { _, _ ->
                viewModel.deleteAllNotes()
                homeAdapter.refresh()
            }
        dialog.create().show()
    }

    override fun clickOnNote(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToFragmentDetailNote(idNote = id))
    }

}