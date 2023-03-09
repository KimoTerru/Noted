package com.kimoterru.noted.presenter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kimoterru.noted.databinding.ItemNoteBinding
import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.presenter.util.NoteClickInterface

class HomeAdapter(
    private val listener: NoteClickInterface
): PagingDataAdapter<NoteItem, HomeAdapter.HomeViewHolder>(DiffUtilCallBack) {

    inner class HomeViewHolder(
        private val binding: ItemNoteBinding,
        private val listener: NoteClickInterface
        ): RecyclerView.ViewHolder(binding.root) {

        fun bind(noteItem: NoteItem) {
            with(binding) {
                titleView.apply {
                    text = noteItem.content
                    setOnClickListener {
                        listener.clickOnNote(noteItem.id)
                    }
                }
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<NoteItem>() {
        override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}