package com.kimoterru.noted.presenter.newNote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kimoterru.noted.R
import com.kimoterru.noted.databinding.FragmentNewNoteBinding
import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.domain.model.NoteItem.Companion.DEFAULT_ID_NOTE
import com.kimoterru.noted.presenter.util.addMenuProvider
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private val binding: FragmentNewNoteBinding by viewBinding()
    private val viewModel: NewNoteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newNoteScreenSetup()
    }

    private fun newNoteScreenSetup() {
        addMenu()
    }

    private fun addMenu() = addMenuProvider(
        menuRes = R.menu.menu_new_note,
        onMenuItemSelected = {
            when (it.itemId) {
                R.id.action_add_new_note -> {
                    viewModel.addNote(getNote(), requireContext())
                    findNavController().popBackStack()
                }
                android.R.id.home -> findNavController().popBackStack()
            }
        }
    )

    private fun getNote(): NoteItem = NoteItem(
        id = DEFAULT_ID_NOTE,
        content = binding.newNoteView.descriptionView.text.toString()
    )
}