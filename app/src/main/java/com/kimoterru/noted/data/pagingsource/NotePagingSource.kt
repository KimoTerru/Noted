package com.kimoterru.noted.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kimoterru.noted.data.local.NoteDao
import com.kimoterru.noted.data.mapper.toNoteItem
import com.kimoterru.noted.domain.model.NoteItem

class NotePagingSource(
    private val noteDao: NoteDao
): PagingSource<Int, NoteItem>() {

    override fun getRefreshKey(state: PagingState<Int, NoteItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NoteItem> {
        return try {
            val pageIndex = params.key ?: 0
            val response = noteDao.getAllNoteFromLocal(
                params.loadSize,
                pageIndex * params.loadSize
            ).map {
                it.toNoteItem()
            }
            LoadResult.Page(
                data = response,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (response.isEmpty()) null else pageIndex + 1
            )
        } catch (t: Throwable) {
            LoadResult.Error(t.fillInStackTrace())
        }
    }
}