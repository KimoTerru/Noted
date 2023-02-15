package com.kimoterru.noted.presenter.detailNote

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kimoterru.noted.R
import com.kimoterru.noted.databinding.FragmentDetailNoteBinding
import com.kimoterru.noted.presenter.util.addMenuProvider

class DetailNoteFragment: Fragment(R.layout.fragment_detail_note) {

    private val binding: FragmentDetailNoteBinding by viewBinding()
    private val viewModel: DetailNoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailNoteScreenSetup()
    }

    private fun detailNoteScreenSetup() = with(binding) {
        addMenu()
    }

    private fun addMenu() = addMenuProvider(
        menuRes = R.menu.menu_detail_note,
        onMenuItemSelected = {
            when (it.itemId) {
                R.id.action_delete -> Toast.makeText(requireContext(), getString(R.string.delete), Toast.LENGTH_SHORT).show()
                R.id.action_update -> Toast.makeText(requireContext(), getString(R.string.update), Toast.LENGTH_SHORT).show()
                android.R.id.home -> findNavController().popBackStack()
            }
        }
    )
}