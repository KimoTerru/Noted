package com.kimoterru.noted.presenter.detailNote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kimoterru.noted.R
import com.kimoterru.noted.databinding.FragmentDetailNoteBinding
import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.presenter.util.addMenuProvider
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailNoteFragment: Fragment(R.layout.fragment_detail_note) {

    private val binding: FragmentDetailNoteBinding by viewBinding()
    private val viewModel: DetailNoteViewModel by viewModel()
    private val args: DetailNoteFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailNoteScreenSetup()
    }

    private fun detailNoteScreenSetup() = with(binding) {
        addMenu()
        viewModel.getNoteFromLocal(args.idNote)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.note.collectLatest { detailNoteView.descriptionView.setText(it) }
        }
    }

    private fun getNote(): NoteItem {
        val newText = binding.detailNoteView.descriptionView.text.toString()
        return NoteItem(id = args.idNote, content = newText)
    }

    private fun addMenu() = addMenuProvider(
        menuRes = R.menu.menu_detail_note,
        onMenuItemSelected = {
            when (it.itemId) {
                R.id.action_delete -> {
                    viewModel.deleteNote(getNote())
                    findNavController().popBackStack()
                }
                R.id.action_update -> {
                    viewModel.updateNote(getNote())
                }
                android.R.id.home -> findNavController().popBackStack()
            }
        }
    )
}