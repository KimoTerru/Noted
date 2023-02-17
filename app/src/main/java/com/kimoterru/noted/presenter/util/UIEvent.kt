package com.kimoterru.noted.presenter.util

import com.kimoterru.noted.domain.model.NoteItem

sealed class UIEvent {
    
    object EmptyField : UIEvent()

    object OperationSuccess : UIEvent()

    object OperationFailed : UIEvent()

    class CanBeRestored(val noteItem: NoteItem) : UIEvent()
    
}