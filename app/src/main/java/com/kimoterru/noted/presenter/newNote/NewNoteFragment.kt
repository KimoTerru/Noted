package com.kimoterru.noted.presenter.newNote

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kimoterru.noted.R
import com.kimoterru.noted.databinding.FragmentNewNoteBinding
import com.kimoterru.noted.presenter.util.addMenuProvider

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private val binding: FragmentNewNoteBinding by viewBinding()
    private val viewModel: NewNoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newNoteScreenSetup()
    }

    private fun newNoteScreenSetup() = with(binding) {
        addMenu()
    }

    private fun addMenu() = addMenuProvider(
        menuRes = R.menu.menu_new_note,
        onMenuItemSelected = {
            when (it.itemId) {
                R.id.action_add_new_note -> Toast.makeText(requireContext(), getText(R.string.add_new_note), Toast.LENGTH_SHORT).show()
                android.R.id.home -> findNavController().popBackStack()
            }
        }
    )
}